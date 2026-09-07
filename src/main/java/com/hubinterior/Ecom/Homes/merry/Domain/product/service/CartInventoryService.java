package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.CartStockDTOs;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Inventory;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import com.hubinterior.Ecom.Homes.merry.Domain.product.repository.ProdDataRepository;
import com.hubinterior.Ecom.Homes.merry.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Enterprise service responsible for Cart inventory validation, reservation (holds),
 * releases, and final order deductions.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CartInventoryService {

    private final ProdDataRepository prodRepo;

    /**
     * Read-only pre-flight verification of stock availability for all items in cart.
     */
    @Transactional(readOnly = true)
    public CartStockDTOs.VerificationRes verifyStock(CartStockDTOs.VerificationReq request) {
        boolean allAvailable = true;
        List<CartStockDTOs.ItemStatus> statuses = new ArrayList<>();

        for (CartStockDTOs.ItemReq item : request.items()) {
            Optional<ProdData> prodOpt = prodRepo.findById(item.productId());

            if (prodOpt.isEmpty()) {
                allAvailable = false;
                statuses.add(CartStockDTOs.ItemStatus.builder()
                        .productId(item.productId())
                        .skuId(item.skuId())
                        .requestedQuantity(item.quantity())
                        .availableStock(0)
                        .isSufficient(false)
                        .message("Product not found")
                        .build());
                continue;
            }

            ProdData prod = prodOpt.get();
            Inventory inventory = prod.getInventory();
            int currentStock = inventory != null ? inventory.getCurrent_stock() : 0;
            boolean isSufficient = currentStock >= item.quantity();

            if (!isSufficient) {
                allAvailable = false;
            }

            statuses.add(CartStockDTOs.ItemStatus.builder()
                    .productId(prod.getProdId())
                    .skuId(prod.getSku_id())
                    .requestedQuantity(item.quantity())
                    .availableStock(currentStock)
                    .isSufficient(isSufficient)
                    .message(isSufficient ? "Stock available" : "Insufficient stock (Available: " + currentStock + ")")
                    .build());
        }

        return CartStockDTOs.VerificationRes.builder()
                .allAvailable(allAvailable)
                .itemStatuses(statuses)
                .build();
    }

    /**
     * Atomically holds stock for a cart session / checkout window with a duration TTL.
     */
    @Transactional
    public CartStockDTOs.HoldRes holdStock(CartStockDTOs.HoldReq request) {
        // Step 1: Pre-check all products for sufficient stock
        for (CartStockDTOs.ItemReq item : request.items()) {
            ProdData prod = prodRepo.findById(item.productId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + item.productId()));

            Inventory inventory = prod.getInventory();
            int currentStock = inventory != null ? inventory.getCurrent_stock() : 0;

            if (currentStock < item.quantity()) {
                return CartStockDTOs.HoldRes.builder()
                        .success(false)
                        .orderReference(request.orderReference())
                        .message("Insufficient stock to place hold on product " + item.productId() +
                                " (Requested: " + item.quantity() + ", Available: " + currentStock + ")")
                        .heldItems(List.of())
                        .build();
            }
        }

        // Step 2: Apply temporary reservation
        String holdRef = "HLD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        int holdMinutes = request.holdDurationMinutes() > 0 ? request.holdDurationMinutes() : 15;
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(holdMinutes);

        List<CartStockDTOs.HeldItemDetail> heldItems = new ArrayList<>();

        for (CartStockDTOs.ItemReq item : request.items()) {
            ProdData prod = prodRepo.findById(item.productId()).orElseThrow();
            Inventory inventory = prod.getInventory();

            int newStock = inventory.getCurrent_stock() - item.quantity();
            inventory.setCurrent_stock(newStock);
            prodRepo.save(prod);

            heldItems.add(CartStockDTOs.HeldItemDetail.builder()
                    .productId(prod.getProdId())
                    .skuId(prod.getSku_id())
                    .heldQuantity(item.quantity())
                    .remainingAvailableStock(newStock)
                    .build());
        }

        log.info("Stock hold placed [Ref: {}] for order [{}] expiring at {}", holdRef, request.orderReference(), expiresAt);

        return CartStockDTOs.HoldRes.builder()
                .success(true)
                .holdReference(holdRef)
                .orderReference(request.orderReference())
                .expiresAt(expiresAt)
                .message("Stock hold placed successfully for " + holdMinutes + " minutes")
                .heldItems(heldItems)
                .build();
    }

    /**
     * Restores held stock back to available inventory upon cart abandonment or hold expiration.
     */
    @Transactional
    public CartStockDTOs.ReleaseRes releaseStock(CartStockDTOs.ReleaseReq request) {
        List<CartStockDTOs.ReleasedItemDetail> releasedDetails = new ArrayList<>();

        for (CartStockDTOs.ItemReq item : request.items()) {
            Optional<ProdData> prodOpt = prodRepo.findById(item.productId());
            if (prodOpt.isPresent()) {
                ProdData prod = prodOpt.get();
                Inventory inventory = prod.getInventory();
                int restoredStock = (inventory != null ? inventory.getCurrent_stock() : 0) + item.quantity();

                if (inventory != null) {
                    inventory.setCurrent_stock(restoredStock);
                    prodRepo.save(prod);
                }

                releasedDetails.add(CartStockDTOs.ReleasedItemDetail.builder()
                        .productId(prod.getProdId())
                        .skuId(prod.getSku_id())
                        .releasedQuantity(item.quantity())
                        .currentStock(restoredStock)
                        .build());
            }
        }

        log.info("Released stock for reference [{}]", request.reference());

        return CartStockDTOs.ReleaseRes.builder()
                .success(true)
                .reference(request.reference())
                .message("Held stock restored successfully to available inventory")
                .releasedItems(releasedDetails)
                .build();
    }

    /**
     * Atomically deducts inventory permanently upon successful order payment.
     */
    @Transactional
    public CartStockDTOs.DeductionRes deductStock(CartStockDTOs.DeductionReq request) {
        List<CartStockDTOs.DeductedItemDetail> deductedDetails = new ArrayList<>();
        List<String> lowStockAlerts = new ArrayList<>();

        // Step 1: Pre-check all products to ensure atomic deduction
        for (CartStockDTOs.ItemReq item : request.items()) {
            ProdData prod = prodRepo.findById(item.productId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + item.productId()));

            Inventory inventory = prod.getInventory();
            int currentStock = inventory != null ? inventory.getCurrent_stock() : 0;

            if (currentStock < item.quantity()) {
                return CartStockDTOs.DeductionRes.builder()
                        .success(false)
                        .orderReference(request.orderReference())
                        .message("Insufficient stock for product id " + item.productId() +
                                " (Requested: " + item.quantity() + ", Available: " + currentStock + ")")
                        .deductedItems(List.of())
                        .lowStockAlerts(List.of())
                        .build();
            }
        }

        // Step 2: Perform stock deduction
        for (CartStockDTOs.ItemReq item : request.items()) {
            ProdData prod = prodRepo.findById(item.productId()).orElseThrow();
            Inventory inventory = prod.getInventory();

            int previousStock = inventory.getCurrent_stock();
            int newStock = previousStock - item.quantity();
            inventory.setCurrent_stock(newStock);

            int minStockLevel = inventory.getMinimum_stock_level();
            boolean lowStockTriggered = newStock <= minStockLevel;

            if (lowStockTriggered) {
                lowStockAlerts.add("Low stock alert for SKU " + prod.getSku_id() +
                        " (Remaining: " + newStock + ", Minimum: " + minStockLevel +
                        ", Reorder Qty: " + inventory.getReorder_quantity() + ")");
            }

            prodRepo.save(prod);

            deductedDetails.add(CartStockDTOs.DeductedItemDetail.builder()
                    .productId(prod.getProdId())
                    .skuId(prod.getSku_id())
                    .deductedQuantity(item.quantity())
                    .remainingStock(newStock)
                    .lowStockTriggered(lowStockTriggered)
                    .build());
        }

        log.info("Deducted stock successfully for order [{}]", request.orderReference());

        return CartStockDTOs.DeductionRes.builder()
                .success(true)
                .orderReference(request.orderReference())
                .message("Inventory stock deducted successfully")
                .deductedItems(deductedDetails)
                .lowStockAlerts(lowStockAlerts)
                .build();
    }
}

package com.hubinterior.Ecom.Homes.merry.Domain.product.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.StockDeductionReqDTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.StockDeductionResDTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.StockVerificationReqDTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.StockVerificationResDTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Inventory;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import com.hubinterior.Ecom.Homes.merry.Domain.product.repository.ProdDataRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/inventory/internal")
@RequiredArgsConstructor
public class InternalInventoryController {

    private final ProdDataRepository prodRepo;

    @PostMapping("/verify-stock")
    public ResponseEntity<StockVerificationResDTO> verifyStock(
            @Valid @RequestBody StockVerificationReqDTO request
    ) {
        boolean allAvailable = true;
        List<StockVerificationResDTO.StockItemStatus> statuses = new ArrayList<>();

        for (StockVerificationReqDTO.StockItemReq item : request.items()) {
            Optional<ProdData> prodOpt = prodRepo.findById(item.productId());

            if (prodOpt.isEmpty()) {
                allAvailable = false;
                statuses.add(StockVerificationResDTO.StockItemStatus.builder()
                        .productId(item.productId())
                        .skuId(item.skuId())
                        .requestedQuantity(item.requestedQuantity())
                        .availableStock(0)
                        .isSufficient(false)
                        .message("Product not found")
                        .build());
                continue;
            }

            ProdData prod = prodOpt.get();
            Inventory inventory = prod.getInventory();
            int currentStock = inventory != null ? inventory.getCurrent_stock() : 0;
            boolean isSufficient = currentStock >= item.requestedQuantity();

            if (!isSufficient) {
                allAvailable = false;
            }

            statuses.add(StockVerificationResDTO.StockItemStatus.builder()
                    .productId(prod.getProdId())
                    .skuId(prod.getSku_id())
                    .requestedQuantity(item.requestedQuantity())
                    .availableStock(currentStock)
                    .isSufficient(isSufficient)
                    .message(isSufficient ? "Stock available" : "Insufficient stock (Available: " + currentStock + ")")
                    .build());
        }

        StockVerificationResDTO response = StockVerificationResDTO.builder()
                .all_available(allAvailable)
                .item_statuses(statuses)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/deduct-stock")
    @Transactional
    public ResponseEntity<StockDeductionResDTO> deductStock(
            @Valid @RequestBody StockDeductionReqDTO request
    ) {
        List<StockDeductionResDTO.DeductedItemDetail> deductedDetails = new ArrayList<>();
        List<String> lowStockAlerts = new ArrayList<>();

        // Step 1: Pre-check all products to ensure atomic deduction
        for (StockDeductionReqDTO.DeductionItem item : request.items()) {
            ProdData prod = prodRepo.findById(item.productId())
                    .orElseThrow(() -> new com.hubinterior.Ecom.Homes.merry.Exception.ResourceNotFoundException("Product not found with id: " + item.productId()));

            Inventory inventory = prod.getInventory();
            int currentStock = inventory != null ? inventory.getCurrent_stock() : 0;

            if (currentStock < item.quantity()) {
                return ResponseEntity.badRequest().body(StockDeductionResDTO.builder()
                        .success(false)
                        .orderReference(request.orderReference())
                        .message("Insufficient stock for product id " + item.productId() + " (Requested: " + item.quantity() + ", Available: " + currentStock + ")")
                        .deductedItems(List.of())
                        .lowStockAlerts(List.of())
                        .build());
            }
        }

        // Step 2: Perform stock deduction
        for (StockDeductionReqDTO.DeductionItem item : request.items()) {
            ProdData prod = prodRepo.findById(item.productId()).orElseThrow();
            Inventory inventory = prod.getInventory();

            int previousStock = inventory.getCurrent_stock();
            int newStock = previousStock - item.quantity();
            inventory.setCurrent_stock(newStock);

            int minStockLevel = inventory.getMinimum_stock_level();
            boolean lowStockTriggered = newStock <= minStockLevel;

            if (lowStockTriggered) {
                lowStockAlerts.add("Low stock alert for SKU " + prod.getSku_id() + " (Remaining: " + newStock + ", Minimum: " + minStockLevel + ", Reorder Qty: " + inventory.getReorder_quantity() + ")");
            }

            prodRepo.save(prod);

            deductedDetails.add(StockDeductionResDTO.DeductedItemDetail.builder()
                    .productId(prod.getProdId())
                    .skuId(prod.getSku_id())
                    .deductedQuantity(item.quantity())
                    .remainingStock(newStock)
                    .lowStockTriggered(lowStockTriggered)
                    .build());
        }

        StockDeductionResDTO response = StockDeductionResDTO.builder()
                .success(true)
                .orderReference(request.orderReference())
                .message("Inventory stock deducted successfully")
                .deductedItems(deductedDetails)
                .lowStockAlerts(lowStockAlerts)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/hold-stock")
    @Transactional
    public ResponseEntity<com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.StockHoldResDTO> holdStock(
            @Valid @RequestBody com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.StockHoldReqDTO request
    ) {
        // Step 1: Verify all products exist and have sufficient stock
        for (com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.StockHoldReqDTO.HoldItem item : request.items()) {
            ProdData prod = prodRepo.findById(item.productId())
                    .orElseThrow(() -> new com.hubinterior.Ecom.Homes.merry.Exception.ResourceNotFoundException("Product not found with id: " + item.productId()));

            Inventory inventory = prod.getInventory();
            int currentStock = inventory != null ? inventory.getCurrent_stock() : 0;

            if (currentStock < item.quantity()) {
                return ResponseEntity.badRequest().body(
                        com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.StockHoldResDTO.builder()
                                .success(false)
                                .orderReference(request.orderReference())
                                .message("Insufficient stock to place hold on product " + item.productId() + " (Requested: " + item.quantity() + ", Available: " + currentStock + ")")
                                .heldItems(List.of())
                                .build()
                );
            }
        }

        // Step 2: Place temporary hold by decrementing available stock
        String holdRef = "HLD-" + java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        int holdMinutes = request.holdDurationMinutes() > 0 ? request.holdDurationMinutes() : 15;
        java.time.LocalDateTime expiresAt = java.time.LocalDateTime.now().plusMinutes(holdMinutes);

        List<com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.StockHoldResDTO.HeldItemDetail> heldItems = new ArrayList<>();

        for (com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.StockHoldReqDTO.HoldItem item : request.items()) {
            ProdData prod = prodRepo.findById(item.productId()).orElseThrow();
            Inventory inventory = prod.getInventory();

            int newStock = inventory.getCurrent_stock() - item.quantity();
            inventory.setCurrent_stock(newStock);
            prodRepo.save(prod);

            heldItems.add(com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.StockHoldResDTO.HeldItemDetail.builder()
                    .productId(prod.getProdId())
                    .skuId(prod.getSku_id())
                    .heldQuantity(item.quantity())
                    .remainingAvailableStock(newStock)
                    .build());
        }

        com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.StockHoldResDTO response =
                com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.StockHoldResDTO.builder()
                        .success(true)
                        .holdReference(holdRef)
                        .orderReference(request.orderReference())
                        .expiresAt(expiresAt)
                        .message("Stock hold placed successfully for " + holdMinutes + " minutes")
                        .heldItems(heldItems)
                        .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/release-stock")
    @Transactional
    public ResponseEntity<com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.StockReleaseResDTO> releaseStock(
            @Valid @RequestBody com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.StockReleaseReqDTO request
    ) {
        List<com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.StockReleaseResDTO.ReleasedItemDetail> releasedDetails = new ArrayList<>();

        for (com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.StockReleaseReqDTO.ReleaseItem item : request.items()) {
            Optional<ProdData> prodOpt = prodRepo.findById(item.productId());
            if (prodOpt.isPresent()) {
                ProdData prod = prodOpt.get();
                Inventory inventory = prod.getInventory();
                int restoredStock = (inventory != null ? inventory.getCurrent_stock() : 0) + item.quantity();
                if (inventory != null) {
                    inventory.setCurrent_stock(restoredStock);
                    prodRepo.save(prod);
                }

                releasedDetails.add(com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.StockReleaseResDTO.ReleasedItemDetail.builder()
                        .productId(prod.getProdId())
                        .skuId(prod.getSku_id())
                        .releasedQuantity(item.quantity())
                        .currentStock(restoredStock)
                        .build());
            }
        }

        com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.StockReleaseResDTO response =
                com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.StockReleaseResDTO.builder()
                        .success(true)
                        .reference(request.reference())
                        .message("Held stock restored successfully to available inventory")
                        .releasedItems(releasedDetails)
                        .build();

        return ResponseEntity.ok(response);
    }
}


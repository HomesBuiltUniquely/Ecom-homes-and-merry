package com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Consolidated Cart & Stock DTOs.
 * Encapsulates all inventory verification, reservation (hold), release, and deduction contracts
 * with reusable line-item records.
 */
public final class CartStockDTOs {

    private CartStockDTOs() {}

    // ==========================================
    // Shared Reusable Line-Item Records
    // ==========================================

    public record ItemReq(
            @NotNull(message = "Product ID is required")
            Long productId,

            String skuId,

            @Min(value = 1, message = "Quantity must be at least 1")
            int quantity
    ) {}

    @Builder
    public record ItemStatus(
            Long productId,
            String skuId,
            int requestedQuantity,
            int availableStock,
            boolean isSufficient,
            String message
    ) {}

    @Builder
    public record HeldItemDetail(
            Long productId,
            String skuId,
            int heldQuantity,
            int remainingAvailableStock
    ) {}

    @Builder
    public record ReleasedItemDetail(
            Long productId,
            String skuId,
            int releasedQuantity,
            int currentStock
    ) {}

    @Builder
    public record DeductedItemDetail(
            Long productId,
            String skuId,
            int deductedQuantity,
            int remainingStock,
            boolean lowStockTriggered
    ) {}

    // ==========================================
    // 1. Cart Stock Verification DTOs
    // ==========================================

    public record VerificationReq(
            @NotEmpty(message = "Items list cannot be empty")
            @Valid
            List<ItemReq> items
    ) {}

    @Builder
    public record VerificationRes(
            @JsonProperty("all_available")
            boolean allAvailable,

            @JsonProperty("item_statuses")
            List<ItemStatus> itemStatuses
    ) {}

    // ==========================================
    // 2. Cart Stock Reservation (Hold) DTOs
    // ==========================================

    public record HoldReq(
            @NotNull(message = "Order reference is required")
            String orderReference,

            @NotEmpty(message = "Hold items cannot be empty")
            @Valid
            List<ItemReq> items,

            @Min(value = 1, message = "Hold minutes must be at least 1")
            int holdDurationMinutes
    ) {}

    @Builder
    public record HoldRes(
            boolean success,
            String holdReference,
            String orderReference,
            LocalDateTime expiresAt,
            String message,
            List<HeldItemDetail> heldItems
    ) {}

    // ==========================================
    // 3. Cart Stock Release DTOs
    // ==========================================

    public record ReleaseReq(
            @NotNull(message = "Hold or order reference is required")
            String reference,

            @NotEmpty(message = "Items to release cannot be empty")
            List<ItemReq> items
    ) {}

    @Builder
    public record ReleaseRes(
            boolean success,
            String reference,
            String message,
            List<ReleasedItemDetail> releasedItems
    ) {}

    // ==========================================
    // 4. Final Stock Deduction DTOs
    // ==========================================

    public record DeductionReq(
            String orderReference,

            @NotEmpty(message = "Deduction items cannot be empty")
            @Valid
            List<ItemReq> items
    ) {}

    @Builder
    public record DeductionRes(
            boolean success,
            String orderReference,
            String message,
            List<DeductedItemDetail> deductedItems,
            List<String> lowStockAlerts
    ) {}
}

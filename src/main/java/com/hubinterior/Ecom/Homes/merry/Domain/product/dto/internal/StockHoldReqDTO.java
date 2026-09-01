package com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record StockHoldReqDTO(
        @NotNull(message = "Order reference is required")
        String orderReference,

        @NotEmpty(message = "Hold items cannot be empty")
        @Valid
        List<HoldItem> items,

        @Min(value = 1, message = "Hold minutes must be at least 1")
        int holdDurationMinutes
) {
    public record HoldItem(
            @NotNull(message = "Product ID is required")
            Long productId,

            @Min(value = 1, message = "Quantity must be at least 1")
            int quantity
    ) {}
}

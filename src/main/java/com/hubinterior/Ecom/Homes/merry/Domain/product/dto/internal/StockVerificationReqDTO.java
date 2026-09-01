package com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record StockVerificationReqDTO(
        @NotEmpty(message = "Items list cannot be empty")
        @Valid
        List<StockItemReq> items
) {
    public record StockItemReq(
            @NotNull(message = "Product ID is required")
            Long productId,

            String skuId,

            @Min(value = 1, message = "Requested quantity must be at least 1")
            int requestedQuantity
    ) {}
}

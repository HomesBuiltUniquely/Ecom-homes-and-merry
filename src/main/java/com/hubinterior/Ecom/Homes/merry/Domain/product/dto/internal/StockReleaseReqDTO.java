package com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record StockReleaseReqDTO(
        @NotNull(message = "Hold or order reference is required")
        String reference,

        @NotEmpty(message = "Items to release cannot be empty")
        List<ReleaseItem> items
) {
    public record ReleaseItem(
            Long productId,
            int quantity
    ) {}
}

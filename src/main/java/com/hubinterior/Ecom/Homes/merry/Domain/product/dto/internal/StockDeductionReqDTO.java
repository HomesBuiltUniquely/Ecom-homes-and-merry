package com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record StockDeductionReqDTO(
        String orderReference,

        @NotEmpty(message = "Deduction items cannot be empty")
        @Valid
        List<DeductionItem> items
) {
    public record DeductionItem(
            @NotNull(message = "Product ID is required")
            Long productId,

            @Min(value = 1, message = "Deduction quantity must be at least 1")
            int quantity
    ) {}
}

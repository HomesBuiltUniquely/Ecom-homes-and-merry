package com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record ProductBatchReqDTO(
        @NotEmpty(message = "Product IDs list cannot be empty")
        List<Long> productIds
) {}

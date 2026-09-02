package com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal;

import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record StockHoldResDTO(
        boolean success,
        String holdReference,
        String orderReference,
        LocalDateTime expiresAt,
        String message,
        List<HeldItemDetail> heldItems
) {
    @Builder
    public record HeldItemDetail(
            Long productId,
            String skuId,
            int heldQuantity,
            int remainingAvailableStock
    ) {}
}

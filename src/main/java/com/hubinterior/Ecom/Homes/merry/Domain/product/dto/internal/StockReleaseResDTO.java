package com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal;

import lombok.Builder;
import java.util.List;

@Builder
public record StockReleaseResDTO(
        boolean success,
        String reference,
        String message,
        List<ReleasedItemDetail> releasedItems
) {
    @Builder
    public record ReleasedItemDetail(
            Long productId,
            String skuId,
            int releasedQuantity,
            int currentStock
    ) {}
}

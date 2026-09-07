package com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal;

import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Gst_Rate;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;

/**
 * Consolidated Cart Product DTOs.
 * Encapsulates batch product retrieval and summary projections specifically for Cart operations.
 */
public final class CartProductDTOs {

    private CartProductDTOs() {}

    /**
     * Request payload to fetch product summaries in batch for cart hydration.
     */
    public record BatchReq(
            @NotEmpty(message = "Product IDs list cannot be empty")
            List<Long> productIds
    ) {}

    /**
     * Lightweight summary projection of a product for rendering cart line items.
     */
    @Builder
    public record SummaryRes(
            Long prodId,
            String sku_id,
            String offering_name,
            String brand,
            Float selling_price,
            Float cost_price,
            Integer discount,
            Gst_Rate gst_rate,
            int current_stock,
            String primary_image_url,
            boolean is_in_stock
    ) {}
}

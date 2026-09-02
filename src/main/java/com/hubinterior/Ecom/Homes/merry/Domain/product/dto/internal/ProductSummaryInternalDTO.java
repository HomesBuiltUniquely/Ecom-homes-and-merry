package com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal;

import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Gst_Rate;
import lombok.Builder;

@Builder
public record ProductSummaryInternalDTO(
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

package com.hubinterior.Ecom.Homes.merry.Domain.product.dto;

import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Gst_Rate;
import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Price_Unit;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record Pricing_Req_DTO(

        @NotNull(message = "Cost price is required")
        @Positive(message = "Cost price must be positive")
        Float cost_price,

        @NotNull(message = "Selling price is required")
        @Positive(message = "Selling price must be positive")
        Float selling_price,

        Integer discount,

        @NotNull(message = "GST rate is required")
        Gst_Rate gst_rate,

        @NotNull(message = "Price unit is required")
        Price_Unit units,

        String desc
) {}

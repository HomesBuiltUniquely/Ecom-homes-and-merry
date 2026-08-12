package com.hubinterior.Ecom.Homes.merry.Domain.product.dto;

import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Gst_Rate;
import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Price_Unit;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;

public record Pricing_Res_DTO(

        Integer price_id,

        Float cost_price,

        Float selling_price,

        Integer discount,

        Gst_Rate gst_rate,

        Price_Unit units,

        Integer margin_percentage,

        String desc
) {}

package com.hubinterior.Ecom.Homes.merry.Domain.product.dto;

import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Preferred_Vendor;

public record Inventory_Res_DTO(

        String sku_Id,



        int current_stock,

        int minimum_stock_level,

        int reorder_quantity,

        SourcingLogistics_Res_DTO sourcingLogistics) {

    public record SourcingLogistics_Res_DTO(

            Preferred_Vendor preferred_vendor,

            Integer lead_time
    ) {}
}

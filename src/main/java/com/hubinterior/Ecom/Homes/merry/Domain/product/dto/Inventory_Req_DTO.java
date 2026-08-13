package com.hubinterior.Ecom.Homes.merry.Domain.product.dto;

import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Preferred_Vendor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record Inventory_Req_DTO(

        @NotBlank(message = "SKU ID cannot be empty") String sku_Id,

        String barcode,

        int current_stock,

        int minimum_stock_level,

        int reorder_quantity,

        @Valid SourcingLogistics_Req_DTO sourcingLogistics) {

    public record SourcingLogistics_Req_DTO(

            @NotNull(message = "Preferred vendor is required")
            Preferred_Vendor preferred_vendor,

            @Positive(message = "Lead time must be positive")
            Integer lead_time
    ) {}
}

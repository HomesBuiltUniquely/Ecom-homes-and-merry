package com.hubinterior.Ecom.Homes.merry.Domain.product.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record Inventory_Req_DTO(

                @NotBlank(message = "SKU ID cannot be empty") String sku_Id,

                String barcode,

                int current_stock,

                int minimum_stock_level,

                int reorder_quantity,

                @Valid SourcingLogistics_Req_DTO sourcing) {
}

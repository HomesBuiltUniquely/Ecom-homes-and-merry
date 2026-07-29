package com.hubinterior.Ecom.Homes.merry.Domain.product.dto;

public record Inventory_Res_DTO(

        String sku_Id,

        String barcode,

        int current_stock,

        int minimum_stock_level,

        int reorder_quantity) {
}

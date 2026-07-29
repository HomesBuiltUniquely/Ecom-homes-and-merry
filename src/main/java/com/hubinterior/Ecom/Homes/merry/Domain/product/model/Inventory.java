package com.hubinterior.Ecom.Homes.merry.Domain.product.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

    private String sku_Id;

    private String barcode;

    private int current_stock;

    private int minimum_stock_level;

    private int reorder_quantity;
}

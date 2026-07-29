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

    private String current_stock;

    private String minimum_stock_level;

    private String reorder_quantity;

    private boolean track_inventory;
}

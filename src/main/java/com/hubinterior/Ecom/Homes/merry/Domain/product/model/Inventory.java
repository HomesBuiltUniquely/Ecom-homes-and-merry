package com.hubinterior.Ecom.Homes.merry.Domain.product.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Inventory {

    @Column(name = "inventory_sku_id")
    private String sku_Id;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "current_stock")
    private int current_stock;

    @Column(name = "minimum_stock_level")
    private int minimum_stock_level;

    @Column(name = "reorder_quantity")
    private int reorder_quantity;
}

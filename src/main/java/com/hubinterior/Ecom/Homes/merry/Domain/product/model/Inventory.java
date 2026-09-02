package com.hubinterior.Ecom.Homes.merry.Domain.product.model;

import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Preferred_Vendor;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Inventory {

    @Column(name = "inventory_sku_id")
    private String sku_Id;

    @Column(name = "current_stock")
    private int current_stock;

    @Column(name = "minimum_stock_level")
    private int minimum_stock_level;

    @Column(name = "reorder_quantity")
    private int reorder_quantity;

    @Embedded
    private SourcingLogistics sourcingLogistics;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class SourcingLogistics {

        @Enumerated(EnumType.STRING)
        @Column(name = "preferred_vendor")
        private Preferred_Vendor preferred_vendor;

        @Column(name = "lead_time")
        private Integer lead_time;
    }
}

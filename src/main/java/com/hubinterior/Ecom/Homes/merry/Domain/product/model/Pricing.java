package com.hubinterior.Ecom.Homes.merry.Domain.product.model;

import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Gst_Rate;
import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Price_Unit;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Pricing {

    @Column(name = "cost_price")
    private Float cost_price;

    @Column(name = "selling_price")
    private Float selling_price;

    @Column(name = "discount")
    private Integer discount;

    @Enumerated(EnumType.STRING)
    @Column(name = "gst_rate")
    private Gst_Rate gst_rate;

    @Enumerated(EnumType.STRING)
    @Column(name = "price_unit")
    private Price_Unit units;

    @Column(name = "margin_percentage")
    private Integer margin_percentage;

    @Column(name = "pricing_desc")
    private String desc;
}

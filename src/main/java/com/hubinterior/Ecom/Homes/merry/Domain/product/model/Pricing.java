package com.hubinterior.Ecom.Homes.merry.Domain.product.model;

import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Gst_Rate;
import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Price_Unit;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pricing {

    /** Auto-generated pricing record ID */
    private Integer price_id;

    /** Cost of goods / procurement price (internal) */
    private Float cost_price;

    /** Customer-facing selling price */
    private Float selling_price;

    /** Discount percentage e.g. 10 = 10% off */
    private Integer discount;

    /** Applicable GST slab: 0 / 5 / 12 / 18 / 28 */
    private Gst_Rate gst_rate;

    /** Unit of measure the price applies to e.g. PER_PIECE, PER_SET */
    private Price_Unit units;

    /**
     * Computed margin % = ((selling_price - cost_price) / selling_price) * 100
     * Recalculate before persist.
     */
    private Integer margin_percentage;

    /** Internal pricing notes / rationale */
    private String desc;
}

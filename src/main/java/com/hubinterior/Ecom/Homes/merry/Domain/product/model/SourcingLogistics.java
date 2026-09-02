package com.hubinterior.Ecom.Homes.merry.Domain.product.model;

import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Preferred_Vendor;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SourcingLogistics {

    @Column(name = "sl_id")
    private Integer sl_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "preferred_vendor")
    private Preferred_Vendor preferred_vendor;

    @Column(name = "lead_time")
    private Integer lead_time;
}

package com.hubinterior.Ecom.Homes.merry.Domain.product.model;

import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Preferred_Vendor;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SourcingLogistics {

    private Integer sl_id;

    private Preferred_Vendor preferred_vendor;

    private Integer lead_time;

}

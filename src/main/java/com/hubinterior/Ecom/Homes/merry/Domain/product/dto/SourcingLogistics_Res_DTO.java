package com.hubinterior.Ecom.Homes.merry.Domain.product.dto;

import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Preferred_Vendor;

public record SourcingLogistics_Res_DTO(

        Integer sl_id,

        Preferred_Vendor preferred_vendor,

        Integer lead_time
) {}

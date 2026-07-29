package com.hubinterior.Ecom.Homes.merry.Domain.product.dto;

import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Preferred_Vendor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SourcingLogistics_Req_DTO(

        @NotNull(message = "Preferred vendor is required")
        Preferred_Vendor preferred_vendor,

        @Positive(message = "Lead time must be positive")
        Integer lead_time
) {}

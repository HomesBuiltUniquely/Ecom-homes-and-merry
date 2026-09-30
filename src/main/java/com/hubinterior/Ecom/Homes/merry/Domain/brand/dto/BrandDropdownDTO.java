package com.hubinterior.Ecom.Homes.merry.Domain.brand.dto;

import com.hubinterior.Ecom.Homes.merry.Domain.brand.enums.BrandStatus;

public record BrandDropdownDTO(
        Long brand_id,
        String brand_name,
        String code,
        String logo_url,
        BrandStatus status
) {}

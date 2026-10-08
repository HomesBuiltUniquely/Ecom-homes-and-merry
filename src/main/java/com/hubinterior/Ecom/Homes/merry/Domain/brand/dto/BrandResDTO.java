package com.hubinterior.Ecom.Homes.merry.Domain.brand.dto;

import com.hubinterior.Ecom.Homes.merry.Domain.brand.enums.BrandStatus;

import java.time.LocalDateTime;
import java.util.List;

public record BrandResDTO(
        Long brand_id,
        String brand_name,
        String manufacturer,
        String code,
        String country,
        String country_code,
        String logo_url,
        BrandStatus status,
        Integer offerings_count,
        List<String> categories,
        String updated_date,
        LocalDateTime created_at
) {}

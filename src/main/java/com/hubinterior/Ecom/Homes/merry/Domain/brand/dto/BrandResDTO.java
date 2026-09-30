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
        String logo_url,
        BrandStatus status,
        Long offerings_count,
        List<String> categories,
        LocalDateTime created_at,
        LocalDateTime updated_at
) {}

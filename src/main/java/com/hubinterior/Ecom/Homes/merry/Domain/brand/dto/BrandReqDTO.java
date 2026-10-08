package com.hubinterior.Ecom.Homes.merry.Domain.brand.dto;

import com.hubinterior.Ecom.Homes.merry.Domain.brand.enums.BrandStatus;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record BrandReqDTO(
        @NotBlank(message = "Brand name cannot be empty")
        String brand_name,

        String manufacturer,

        @NotBlank(message = "Brand code cannot be empty")
        String code,

        String country,

        String country_code,

        String logo_url,

        BrandStatus status,

        List<String> categories
) {}

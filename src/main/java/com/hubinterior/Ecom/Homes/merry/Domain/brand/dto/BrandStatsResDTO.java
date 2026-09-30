package com.hubinterior.Ecom.Homes.merry.Domain.brand.dto;

public record BrandStatsResDTO(
        long total_brands,
        long active_offerings,
        long countries_count,
        long pending_review
) {}

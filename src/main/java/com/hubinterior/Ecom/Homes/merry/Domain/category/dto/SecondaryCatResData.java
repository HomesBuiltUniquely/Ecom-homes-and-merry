package com.hubinterior.Ecom.Homes.merry.Domain.category.dto;

import java.util.List;

public record SecondaryCatResData(
        Long secondaryCategoryId,
        String secondaryCategoryName,
        String secondaryCategoryDescription,
        List<SecondaryCatResData> subCategory
) {
}

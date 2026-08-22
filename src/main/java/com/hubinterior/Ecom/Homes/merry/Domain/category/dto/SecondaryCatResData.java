package com.hubinterior.Ecom.Homes.merry.Domain.category.dto;

import com.hubinterior.Ecom.Homes.merry.Domain.category.model.SecondaryCategory;

import java.util.List;

public record SecondaryCatResData(
        Long secondaryCategoryId,
        String secondaryCategoryName,
        String secondaryCategoryDescription,
        List<SecondaryCatResData> subCategory,
        SecondaryCategory parent
) {
}

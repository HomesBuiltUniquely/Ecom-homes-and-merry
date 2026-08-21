package com.hubinterior.Ecom.Homes.merry.Domain.category.dto;

import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;

import java.util.List;

public record SecondaryCatResData(
        Long secondaryCategoryId,
        String secondaryCategoryName,
        String secondaryCategoryDescription,
        List<SecondaryCatResData> subCategory,
        List<ProdData> products
) {
}

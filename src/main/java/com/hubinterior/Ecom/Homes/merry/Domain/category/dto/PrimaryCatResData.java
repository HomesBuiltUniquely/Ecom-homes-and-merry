package com.hubinterior.Ecom.Homes.merry.Domain.category.dto;

import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;

import java.util.List;

public record PrimaryCatResData(
        Long primaryCategoryId,
        String primaryCategoryName,
        String primaryCategoryDescription,
        List<SecondaryCatResData> subCategory,
        List<ProdData> products
) {
}

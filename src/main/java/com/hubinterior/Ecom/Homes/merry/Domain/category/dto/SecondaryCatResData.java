package com.hubinterior.Ecom.Homes.merry.Domain.category.dto;

import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.SEO;

import java.util.List;

public record SecondaryCatResData(
        Long secondaryCategoryId,
        String secondaryCategoryName,
        String secondaryCategoryDescription,
        String imageUrl,
        SEO seo,
        List<String> internalTags,
        List<SecondaryCatResData> subCategory,
        List<ProdData> products
) {
}

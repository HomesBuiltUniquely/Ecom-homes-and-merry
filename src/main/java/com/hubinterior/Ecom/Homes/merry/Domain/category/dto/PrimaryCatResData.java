package com.hubinterior.Ecom.Homes.merry.Domain.category.dto;

import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.SEO;

import java.util.List;

public record PrimaryCatResData(
        Long primaryCategoryId,
        String primaryCategoryName,
        String primaryCategoryDescription,
        String imageUrl,
        SEO seo,
        List<String> internalTags,
        List<SecondaryCatResData> subCategory,
        List<ProdData> products
) {
}

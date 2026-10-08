package com.hubinterior.Ecom.Homes.merry.Domain.category.dto;

import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.SEO;
import jakarta.validation.Valid;

import java.util.List;

public record SecondaryCatReqData(
        String secondaryCategoryName,
        String secondaryCategoryDescription,
        String imageUrl,
        @Valid SEO seo,
        List<String> internalTags,
        List<SecondaryCatReqData> subCategory,
        List<ProdData> products
) {
}

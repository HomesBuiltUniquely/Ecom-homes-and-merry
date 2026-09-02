package com.hubinterior.Ecom.Homes.merry.Domain.category.dto;

import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;

import java.util.List;

public record PrimaryCatReqData(
        String primaryCategoryName,
        String primaryCategoryDescription,
        List<SecondaryCatReqData> subCategory,
        List<ProdData> products
) {
}

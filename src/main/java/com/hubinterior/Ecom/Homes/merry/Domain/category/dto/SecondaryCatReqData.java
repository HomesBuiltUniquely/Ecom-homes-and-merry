package com.hubinterior.Ecom.Homes.merry.Domain.category.dto;

import java.util.List;

public record SecondaryCatReqData(
        String secondaryCategoryName,
        String secondaryCategoryDescription,
        List<SecondaryCatReqData> subCategory
) {
}

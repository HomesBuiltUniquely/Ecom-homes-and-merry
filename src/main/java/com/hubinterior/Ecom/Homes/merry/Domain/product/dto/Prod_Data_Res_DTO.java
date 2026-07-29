package com.hubinterior.Ecom.Homes.merry.Domain.product.dto;

import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Offering_Category;
import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Offering_Type;

import java.util.List;

public record Prod_Data_Res_DTO(

        Long prod_id,

        String offering_name,

        Offering_Type offering_type,

        String sku_id,

        Offering_Category category,

        String brand,

        List<String> tags,

        String short_desc,

        boolean featured_offer
) {}

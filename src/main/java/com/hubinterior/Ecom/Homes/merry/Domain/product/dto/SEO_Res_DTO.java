package com.hubinterior.Ecom.Homes.merry.Domain.product.dto;

import java.util.List;

public record SEO_Res_DTO(

        String page_title,

        String meta_desc,

        String url_slug,

        List<String> keywords
) {}

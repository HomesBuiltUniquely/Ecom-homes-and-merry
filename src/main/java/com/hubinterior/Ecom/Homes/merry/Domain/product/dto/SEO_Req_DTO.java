package com.hubinterior.Ecom.Homes.merry.Domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record SEO_Req_DTO(

        @NotBlank(message = "Page title is required")
        @Size(max = 60, message = "Page title must not exceed 60 characters")
        String page_title,

        @Size(max = 160, message = "Meta description must not exceed 160 characters")
        String meta_desc,

        @NotBlank(message = "URL slug is required")
        String url_slug,

        List<String> keywords
) {}

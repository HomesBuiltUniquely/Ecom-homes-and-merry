package com.hubinterior.Ecom.Homes.merry.Domain.product.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record Media_Req_DTO(

        @NotBlank(message = "Primary image is required")
        String primary_image,

        List<String> gallery_images,

        String video_link,

        String image_360,

        String product_brochure,

        String upload_draw
) {}

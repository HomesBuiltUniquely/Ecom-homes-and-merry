package com.hubinterior.Ecom.Homes.merry.Domain.product.dto;

import java.util.List;

public record Media_Res_DTO(

        Integer media_id,

        String primary_image,

        List<String> gallery_images,

        String video_link,

        String image_360,

        String product_brochure,

        String upload_draw
) {}

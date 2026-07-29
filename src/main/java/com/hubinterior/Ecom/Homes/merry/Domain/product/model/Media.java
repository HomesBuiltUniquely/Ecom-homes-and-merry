package com.hubinterior.Ecom.Homes.merry.Domain.product.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Media {

    private Integer media_id;

    private String primary_image;

    private List<String> gallery_images;

    private String video_link;

    private String image_360;

    private String product_brochure;

    private String upload_draw;
}

package com.hubinterior.Ecom.Homes.merry.Domain.product.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Media {

    @Column(name = "primary_image")
    private String primary_image;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "gallery_images", columnDefinition = "json")
    @Builder.Default
    private List<String> gallery_images = new ArrayList<>();

    @Column(name = "video_link")
    private String video_link;

    @Column(name = "image_360")
    private String image_360;

    @Column(name = "product_brochure")
    private String product_brochure;

    @Column(name = "upload_draw")
    private String upload_draw;
}

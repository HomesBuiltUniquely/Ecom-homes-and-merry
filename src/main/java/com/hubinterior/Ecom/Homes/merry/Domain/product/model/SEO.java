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
public class SEO {

    @Column(name = "page_title")
    private String page_title;

    @Column(name = "meta_desc", length = 500)
    private String meta_desc;

    @Column(name = "url_slug")
    private String url_slug;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "seo_keywords", columnDefinition = "json")
    @Builder.Default
    private List<String> keywords = new ArrayList<>();
}

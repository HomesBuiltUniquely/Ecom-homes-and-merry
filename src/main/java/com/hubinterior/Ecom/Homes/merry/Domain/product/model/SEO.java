package com.hubinterior.Ecom.Homes.merry.Domain.product.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SEO {

    private String page_title;

    private String meta_desc;

    private String url_slug;

    private List<String> keywords;
}

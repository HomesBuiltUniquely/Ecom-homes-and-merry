package com.hubinterior.Ecom.Homes.merry.Domain.product.model;

import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Offering_Category;
import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Offering_Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Prod_data {

    private Long prod_id;

    @NotBlank
    private String offering_name;

    @NotNull
    private Offering_Type offering_type;

    @NotBlank
    private String sku_id;

    @NotNull
    private Offering_Category category;

    private String brand;

    private List<String> tags;

    private String short_desc;

    private String long_desc;

    private boolean featured_offer;

    private Pricing pricing;

    private Inventory inventory;

    private SourcingLogistics sourcingLogistics;

    private Media media;

    private Specifications specifications;

    private SEO seo;

    private Internal internal;
}

package com.hubinterior.Ecom.Homes.merry.Domain.product.model;

import com.hubinterior.Ecom.Homes.merry.Domain.category.model.PrimaryCategory;
import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Offering_Category;
import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Offering_Type;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class ProdData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private Long prod_id;

    @NotBlank
    @Column(name = "offering_name", nullable = false)
    private String offering_name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "offering_type", nullable = false)
    private Offering_Type offering_type;

    @NotBlank
    @Column(name = "sku_id", nullable = false, unique = true)
    private String sku_id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Offering_Category category;

    @Column(name = "brand")
    private String brand;

    @ElementCollection
    @CollectionTable(name = "product_tags", joinColumns = @JoinColumn(name = "prod_id"))
    @Column(name = "tag")
    @Builder.Default
    private List<String> tags = new ArrayList<>();

    @Column(name = "short_desc", length = 500)
    private String short_desc;

    @Column(name = "long_desc", columnDefinition = "TEXT")
    private String long_desc;

    @Column(name = "featured_offer")
    private boolean featured_offer;

    @Embedded
    private Pricing pricing;

    @Embedded
    private Inventory inventory;

    @Embedded
    private Media media;

    @Embedded
    private Specifications specifications;

    @Embedded
    private SEO seo;

    @Embedded
    private Internal internal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primary_category_id")
    private PrimaryCategory primaryCategory;
}

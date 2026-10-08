package com.hubinterior.Ecom.Homes.merry.Domain.category.model;

import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.SEO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "secondary_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecondaryCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scatId")
    private Long secondaryCategoryId;

    @Column(name = "scatName")
    private String secondaryCategoryName;

    @Column(name = "scatDesc")
    private String secondaryCategoryDescription;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Embedded
    private SEO seo;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "internal_tags", columnDefinition = "json")
    @Builder.Default
    private List<String> internalTags = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primary_category_id")
    private PrimaryCategory primaryCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_scat_id")
    private SecondaryCategory parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<SecondaryCategory> subCategory = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "secondary_category_id")
    @Builder.Default
    private List<ProdData> products = new ArrayList<>();
}

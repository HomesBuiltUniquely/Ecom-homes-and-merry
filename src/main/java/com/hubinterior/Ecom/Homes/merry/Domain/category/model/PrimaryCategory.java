package com.hubinterior.Ecom.Homes.merry.Domain.category.model;

import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrimaryCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pcatId")
    private Long primaryCategoryId;

    @Column(name = "pcatName")
    private String primaryCategoryName;

    @Column(name = "pcatDesc")
    private String primaryCategoryDescription;

    @OneToMany(mappedBy = "primaryCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<SecondaryCategory> subCategory = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "primary_category_id")
    @Builder.Default
    private List<ProdData> Products = new ArrayList<>();
}

package com.hubinterior.Ecom.Homes.merry.Domain.brand.model;

import com.hubinterior.Ecom.Homes.merry.Domain.brand.enums.BrandStatus;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brands")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long brandId;

    @NotBlank
    @Column(name = "brand_name", nullable = false)
    private String brandName;

    @Column(name = "manufacturer")
    private String manufacturer;

    @NotBlank
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "country")
    private String country;

    @Column(name = "logo_url")
    private String logoUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Builder.Default
    private BrandStatus status = BrandStatus.ACTIVE;

    @ElementCollection
    @CollectionTable(name = "brand_categories", joinColumns = @JoinColumn(name = "brand_id"))
    @Column(name = "category_name")
    @Builder.Default
    private List<String> categories = new ArrayList<>();

    @OneToMany(mappedBy = "brandEntity", fetch = FetchType.LAZY)
    @Builder.Default
    private List<ProdData> products = new ArrayList<>();

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = BrandStatus.ACTIVE;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

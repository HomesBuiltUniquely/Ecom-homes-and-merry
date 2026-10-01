package com.hubinterior.Ecom.Homes.merry.Domain.brand.model;

import com.hubinterior.Ecom.Homes.merry.Domain.brand.enums.BrandStatus;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @Column(name = "id")
    private Long id;

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

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "offerings_count")
    @Builder.Default
    private Integer offeringsCount = 0;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "categories", columnDefinition = "json")
    @Builder.Default
    private List<String> categories = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Builder.Default
    private BrandStatus status = BrandStatus.ACTIVE;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "updated_date")
    private String updatedDate;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "brandEntity", fetch = FetchType.LAZY)
    @Builder.Default
    private List<ProdData> products = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.updatedDate == null) {
            this.updatedDate = DateTimeFormatter.ofPattern("MMM dd, yyyy").format(this.createdAt);
        }
        if (this.status == null) {
            this.status = BrandStatus.ACTIVE;
        }
        if (this.offeringsCount == null) {
            this.offeringsCount = 0;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = DateTimeFormatter.ofPattern("MMM dd, yyyy").format(LocalDateTime.now());
    }
}

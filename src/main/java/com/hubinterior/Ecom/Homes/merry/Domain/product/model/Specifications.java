package com.hubinterior.Ecom.Homes.merry.Domain.product.model;

import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Finish_Type;
import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Load_Capacity;
import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Primary_Material;
import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Secondary_Material;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Specifications {

    @Embedded
    private Physical_Dimensions physical_dimensions;

    @Embedded
    private Material_Finish material_finish;

    @Embedded
    private Technical_Properties technical_properties;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "additional_attributes", columnDefinition = "json")
    @Builder.Default
    private List<Additional_Attribute> additional_attributes = new ArrayList<>();

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class Physical_Dimensions {

        @Column(name = "length_cm")
        private Float length;

        @Column(name = "width_cm")
        private Float width;

        @Column(name = "height_cm")
        private Float height;

        @Column(name = "weight_kg")
        private Float weight;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class Material_Finish {

        @Enumerated(EnumType.STRING)
        @Column(name = "primary_material")
        private Primary_Material primary_material;

        @Enumerated(EnumType.STRING)
        @Column(name = "secondary_material")
        private Secondary_Material secondary_material;

        @Enumerated(EnumType.STRING)
        @Column(name = "finish_type")
        private Finish_Type finish_type;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class Technical_Properties {

        @Column(name = "assembly_required")
        private Boolean assembly_required;

        @Enumerated(EnumType.STRING)
        @Column(name = "load_capacity")
        private Load_Capacity load_capacity;

        @Column(name = "tech_desc")
        private String desc;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Additional_Attribute {

        private String attribute_name;
        private String value;
    }
}

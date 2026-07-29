package com.hubinterior.Ecom.Homes.merry.Domain.product.model;

import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Finish_Type;
import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Load_Capacity;
import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Primary_Material;
import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Secondary_Material;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Specifications {

    private Physical_Dimensions physical_dimensions;

    private Material_Finish material_finish;

    private Technical_Properties technical_properties;

    private List<Additional_Attribute> additional_attributes;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Physical_Dimensions {

        private Float length; // cm
        private Float width; // cm
        private Float height; // cm
        private Float weight; // kg
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Material_Finish {

        private Primary_Material primary_material;
        private Secondary_Material secondary_material;
        private Finish_Type finish_type;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Technical_Properties {

        private Boolean assembly_required;

        private Load_Capacity load_capacity;

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

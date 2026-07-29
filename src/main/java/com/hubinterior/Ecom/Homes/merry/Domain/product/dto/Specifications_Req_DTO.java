package com.hubinterior.Ecom.Homes.merry.Domain.product.dto;

import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Finish_Type;
import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Load_Capacity;
import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Primary_Material;
import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Secondary_Material;

import java.util.List;

public record Specifications_Req_DTO(

        Physical_Dimensions_DTO physical_dimensions,

        Material_Finish_DTO material_finish,

        Technical_Properties_DTO technical_properties,

        List<Additional_Attribute_DTO> additional_attributes

) {
    public record Physical_Dimensions_DTO(
            Float length,
            Float width,
            Float height,
            Float weight
    ) {}

    public record Material_Finish_DTO(
            Primary_Material primary_material,
            Secondary_Material secondary_material,
            Finish_Type finish_type
    ) {}

    public record Technical_Properties_DTO(
            Boolean assembly_required,
            Load_Capacity load_capacity,
            String desc
    ) {}

    public record Additional_Attribute_DTO(
            String attribute_name,
            String value
    ) {}
}

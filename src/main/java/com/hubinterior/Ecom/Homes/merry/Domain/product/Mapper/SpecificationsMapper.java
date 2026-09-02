package com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Specifications_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Specifications_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Specifications;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SpecificationsMapper {

    @Mapping(source = "physical_dimensions", target = "physical_dimensions")
    @Mapping(source = "material_finish", target = "material_finish")
    @Mapping(source = "technical_properties", target = "technical_properties")
    @Mapping(source = "additional_attributes", target = "additional_attributes")
    Specifications toEntity(Specifications_Req_DTO req);

    @Mapping(source = "physical_dimensions", target = "physical_dimensions")
    @Mapping(source = "material_finish", target = "material_finish")
    @Mapping(source = "technical_properties", target = "technical_properties")
    @Mapping(source = "additional_attributes", target = "additional_attributes")
    Specifications_Res_DTO toResponseDto(Specifications entity);
}

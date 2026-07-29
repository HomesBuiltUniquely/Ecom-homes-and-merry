package com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Specifications_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Specifications_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Specifications;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpecificationsMapper {

    // Nested record → nested static class — MapStruct maps by matching field names
    Specifications toEntity(Specifications_Req_DTO req);

    Specifications_Res_DTO toResponseDto(Specifications entity);
}

package com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Internal_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Internal_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Internal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InternalMapper {

    // Nested record → nested static class — MapStruct maps by matching field names
    Internal toEntity(Internal_Req_DTO req);

    Internal_Res_DTO toResponseDto(Internal entity);
}

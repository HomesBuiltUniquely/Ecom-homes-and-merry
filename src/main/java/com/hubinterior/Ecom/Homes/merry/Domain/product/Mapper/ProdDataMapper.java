package com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Prod_Data_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Prod_Data_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProdDataMapper {

    @Mapping(target = "prod_id", ignore = true)
    ProdData toEntity(Prod_Data_Req_DTO req);

    @Mapping(target = "prod_id", ignore = true)
    void updateEntityFromDto(Prod_Data_Req_DTO req, @MappingTarget ProdData entity);

    Prod_Data_Res_DTO toResponseDto(ProdData entity);
}

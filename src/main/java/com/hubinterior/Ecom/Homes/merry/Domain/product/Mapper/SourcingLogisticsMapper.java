package com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.SourcingLogistics_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.SourcingLogistics_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.SourcingLogistics;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SourcingLogisticsMapper {

    // sl_id is system-generated
    @Mapping(target = "sl_id", ignore = true)
    SourcingLogistics toEntity(SourcingLogistics_Req_DTO req);

    SourcingLogistics_Res_DTO toResponseDto(SourcingLogistics entity);
}

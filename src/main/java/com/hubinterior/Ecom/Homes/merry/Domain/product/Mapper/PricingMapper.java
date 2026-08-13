package com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Pricing_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Pricing_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Pricing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PricingMapper {

    // margin_percentage is system-generated
    @Mapping(target = "margin_percentage", ignore = true)
    Pricing toEntity(Pricing_Req_DTO req);

    Pricing_Res_DTO toResponseDto(Pricing entity);
}

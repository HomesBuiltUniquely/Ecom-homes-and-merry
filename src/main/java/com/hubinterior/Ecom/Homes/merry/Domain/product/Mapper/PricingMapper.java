package com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Pricing_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Pricing_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Pricing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PricingMapper {

    // price_id and margin_percentage are system-generated
    @Mapping(target = "price_id", ignore = true)
    @Mapping(target = "margin_percentage", ignore = true)
    Pricing toEntity(Pricing_Req_DTO req);

    Pricing_Res_DTO toResponseDto(Pricing entity);
}

package com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Pricing_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Pricing_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Pricing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PricingMapper {

    // price_id, margin_percentage, and parent product are system-managed
    @Mapping(target = "price_id", ignore = true)
    @Mapping(target = "margin_percentage", ignore = true)
    @Mapping(target = "product", ignore = true)
    Pricing toEntity(Pricing_Req_DTO req);

    Pricing_Res_DTO toResponseDto(Pricing entity);
}

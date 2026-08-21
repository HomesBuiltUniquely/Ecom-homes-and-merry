package com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Pricing_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Pricing_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Pricing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PricingMapper {

    @Mapping(target = "margin_percentage", ignore = true)
    @Mapping(source = "cost_price", target = "cost_price")
    @Mapping(source = "selling_price", target = "selling_price")
    @Mapping(source = "discount", target = "discount")
    @Mapping(source = "gst_rate", target = "gst_rate")
    @Mapping(source = "units", target = "units")
    @Mapping(source = "desc", target = "desc")
    Pricing toEntity(Pricing_Req_DTO req);

    @Mapping(source = "cost_price", target = "cost_price")
    @Mapping(source = "selling_price", target = "selling_price")
    @Mapping(source = "discount", target = "discount")
    @Mapping(source = "gst_rate", target = "gst_rate")
    @Mapping(source = "units", target = "units")
    @Mapping(source = "margin_percentage", target = "margin_percentage")
    @Mapping(source = "desc", target = "desc")
    Pricing_Res_DTO toResponseDto(Pricing entity);
}

package com.hubinterior.Ecom.Homes.merry.Domain.brand.mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.brand.dto.BrandDropdownDTO;
import com.hubinterior.Ecom.Homes.merry.Domain.brand.dto.BrandReqDTO;
import com.hubinterior.Ecom.Homes.merry.Domain.brand.dto.BrandResDTO;
import com.hubinterior.Ecom.Homes.merry.Domain.brand.model.Brand;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BrandMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "offeringsCount", ignore = true)
    @Mapping(source = "brand_name", target = "brandName")
    @Mapping(source = "manufacturer", target = "manufacturer")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "country_code", target = "countryCode")
    @Mapping(source = "logo_url", target = "logoUrl")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "categories", target = "categories")
    Brand toEntity(BrandReqDTO req);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "offeringsCount", ignore = true)
    @Mapping(source = "brand_name", target = "brandName")
    @Mapping(source = "manufacturer", target = "manufacturer")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "country_code", target = "countryCode")
    @Mapping(source = "logo_url", target = "logoUrl")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "categories", target = "categories")
    void updateEntityFromDto(BrandReqDTO req, @MappingTarget Brand entity);

    @Mapping(source = "id", target = "brand_id")
    @Mapping(source = "brandName", target = "brand_name")
    @Mapping(source = "manufacturer", target = "manufacturer")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "countryCode", target = "country_code")
    @Mapping(source = "logoUrl", target = "logo_url")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "offeringsCount", target = "offerings_count")
    @Mapping(source = "categories", target = "categories")
    @Mapping(source = "updatedDate", target = "updated_date")
    @Mapping(source = "createdAt", target = "created_at")
    BrandResDTO toResponseDto(Brand entity);

    @Mapping(source = "id", target = "brand_id")
    @Mapping(source = "brandName", target = "brand_name")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "logoUrl", target = "logo_url")
    @Mapping(source = "status", target = "status")
    BrandDropdownDTO toDropdownDto(Brand entity);
}

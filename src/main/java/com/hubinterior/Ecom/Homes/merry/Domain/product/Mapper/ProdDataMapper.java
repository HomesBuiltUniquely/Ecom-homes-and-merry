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
    @Mapping(source = "offering_name", target = "offering_name")
    @Mapping(source = "offering_type", target = "offering_type")
    @Mapping(source = "sku_id", target = "sku_id")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "tags", target = "tags")
    @Mapping(source = "short_desc", target = "short_desc")
    @Mapping(source = "long_desc", target = "long_desc")
    @Mapping(source = "featured_offer", target = "featured_offer")
    @Mapping(source = "pricing", target = "pricing")
    @Mapping(source = "inventory", target = "inventory")
    @Mapping(source = "media", target = "media")
    @Mapping(source = "specifications", target = "specifications")
    @Mapping(source = "seo", target = "seo")
    @Mapping(source = "internal", target = "internal")
    ProdData toEntity(Prod_Data_Req_DTO req);

    @Mapping(target = "prod_id", ignore = true)
    @Mapping(source = "offering_name", target = "offering_name")
    @Mapping(source = "offering_type", target = "offering_type")
    @Mapping(source = "sku_id", target = "sku_id")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "tags", target = "tags")
    @Mapping(source = "short_desc", target = "short_desc")
    @Mapping(source = "long_desc", target = "long_desc")
    @Mapping(source = "featured_offer", target = "featured_offer")
    @Mapping(source = "pricing", target = "pricing")
    @Mapping(source = "inventory", target = "inventory")
    @Mapping(source = "media", target = "media")
    @Mapping(source = "specifications", target = "specifications")
    @Mapping(source = "seo", target = "seo")
    @Mapping(source = "internal", target = "internal")
    void updateEntityFromDto(Prod_Data_Req_DTO req, @MappingTarget ProdData entity);

    @Mapping(source = "prod_id", target = "prod_id")
    @Mapping(source = "offering_name", target = "offering_name")
    @Mapping(source = "offering_type", target = "offering_type")
    @Mapping(source = "pricing", target = "pricing")
    @Mapping(source = "sku_id", target = "sku_id")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "tags", target = "tags")
    @Mapping(source = "short_desc", target = "short_desc")
    @Mapping(source = "featured_offer", target = "featured_offer")
    Prod_Data_Res_DTO toResponseDto(ProdData entity);
}

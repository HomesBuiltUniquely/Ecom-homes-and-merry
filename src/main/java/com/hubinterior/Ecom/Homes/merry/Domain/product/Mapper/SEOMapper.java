package com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.SEO_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.SEO_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.SEO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SEOMapper {

    @Mapping(source = "page_title", target = "page_title")
    @Mapping(source = "meta_desc", target = "meta_desc")
    @Mapping(source = "url_slug", target = "url_slug")
    @Mapping(source = "keywords", target = "keywords")
    SEO toEntity(SEO_Req_DTO req);

    @Mapping(source = "page_title", target = "page_title")
    @Mapping(source = "meta_desc", target = "meta_desc")
    @Mapping(source = "url_slug", target = "url_slug")
    @Mapping(source = "keywords", target = "keywords")
    SEO_Res_DTO toResponseDto(SEO entity);
}

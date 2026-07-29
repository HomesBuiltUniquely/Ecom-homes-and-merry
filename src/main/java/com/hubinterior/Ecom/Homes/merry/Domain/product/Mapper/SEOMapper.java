package com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.SEO_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.SEO_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.SEO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SEOMapper {

    SEO toEntity(SEO_Req_DTO req);

    SEO_Res_DTO toResponseDto(SEO entity);
}

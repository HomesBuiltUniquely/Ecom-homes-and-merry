package com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Media_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Media_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Media;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MediaMapper {

    Media toEntity(Media_Req_DTO req);

    Media_Res_DTO toResponseDto(Media entity);
}

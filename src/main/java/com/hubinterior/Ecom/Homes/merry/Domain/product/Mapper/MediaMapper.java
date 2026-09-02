package com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Media_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Media_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Media;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MediaMapper {

    @Mapping(source = "primary_image", target = "primary_image")
    @Mapping(source = "gallery_images", target = "gallery_images")
    @Mapping(source = "video_link", target = "video_link")
    @Mapping(source = "image_360", target = "image_360")
    @Mapping(source = "product_brochure", target = "product_brochure")
    @Mapping(source = "upload_draw", target = "upload_draw")
    Media toEntity(Media_Req_DTO req);

    @Mapping(source = "primary_image", target = "primary_image")
    @Mapping(source = "gallery_images", target = "gallery_images")
    @Mapping(source = "video_link", target = "video_link")
    @Mapping(source = "image_360", target = "image_360")
    @Mapping(source = "product_brochure", target = "product_brochure")
    @Mapping(source = "upload_draw", target = "upload_draw")
    Media_Res_DTO toResponseDto(Media entity);
}

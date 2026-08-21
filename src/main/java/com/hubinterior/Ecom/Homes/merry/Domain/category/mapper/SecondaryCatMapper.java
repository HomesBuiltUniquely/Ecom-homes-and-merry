package com.hubinterior.Ecom.Homes.merry.Domain.category.mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.category.dto.SecondaryCatReqData;
import com.hubinterior.Ecom.Homes.merry.Domain.category.dto.SecondaryCatResData;
import com.hubinterior.Ecom.Homes.merry.Domain.category.model.SecondaryCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SecondaryCatMapper {

    @Mapping(target = "secondaryCategoryId", ignore = true)
    @Mapping(target = "primaryCategory", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(source = "secondaryCategoryName", target = "secondaryCategoryName")
    @Mapping(source = "secondaryCategoryDescription", target = "secondaryCategoryDescription")
    @Mapping(source = "subCategory", target = "subCategory")
    @Mapping(source = "products", target = "products")
    SecondaryCategory toEntity(SecondaryCatReqData req);

    @Mapping(source = "secondaryCategoryId", target = "secondaryCategoryId")
    @Mapping(source = "secondaryCategoryName", target = "secondaryCategoryName")
    @Mapping(source = "secondaryCategoryDescription", target = "secondaryCategoryDescription")
    @Mapping(source = "subCategory", target = "subCategory")
    @Mapping(source = "products", target = "products")
    SecondaryCatResData toResponseDto(SecondaryCategory entity);
}

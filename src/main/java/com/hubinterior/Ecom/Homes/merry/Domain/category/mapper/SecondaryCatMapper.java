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
    @Mapping(target = "products", ignore = true)
    SecondaryCategory toEntity(SecondaryCatReqData req);

    SecondaryCatResData toResponseDto(SecondaryCategory entity);
}

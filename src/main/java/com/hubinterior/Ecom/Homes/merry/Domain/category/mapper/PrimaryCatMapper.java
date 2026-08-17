package com.hubinterior.Ecom.Homes.merry.Domain.category.mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.category.dto.PrimaryCatReqData;
import com.hubinterior.Ecom.Homes.merry.Domain.category.dto.PrimaryCatResData;
import com.hubinterior.Ecom.Homes.merry.Domain.category.model.PrimaryCategory;
import com.hubinterior.Ecom.Homes.merry.Domain.category.model.SecondaryCategory;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = SecondaryCatMapper.class)
public interface PrimaryCatMapper {

    @Mapping(target = "primaryCategoryId", ignore = true)
    @Mapping(source = "products", target = "Products")
    PrimaryCategory toEntity(PrimaryCatReqData req);

    PrimaryCatResData toResponseDto(PrimaryCategory entity);

    @AfterMapping
    default void linkSubCategories(@MappingTarget PrimaryCategory entity) {
        if (entity.getSubCategory() == null) {
            return;
        }
        entity.getSubCategory().forEach(sub -> {
            sub.setPrimaryCategory(entity);
            linkNestedSubCategories(sub);
        });
    }

    default void linkNestedSubCategories(SecondaryCategory parent) {
        if (parent.getSubCategory() == null) {
            return;
        }
        parent.getSubCategory().forEach(child -> {
            child.setParent(parent);
            linkNestedSubCategories(child);
        });
    }
}

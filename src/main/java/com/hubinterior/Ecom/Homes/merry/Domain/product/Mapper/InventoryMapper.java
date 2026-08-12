package com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Inventory_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Inventory_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = SourcingLogisticsMapper.class)
public interface InventoryMapper {

    // Map the nested sourcing DTO → SourcingLogistics entity
    @Mapping(target = "sourcing", source = "sourcing")
    Inventory toEntity(Inventory_Req_DTO req);

    // Map the nested SourcingLogistics entity → sourcing DTO
    @Mapping(target = "sourcing", source = "sourcing")
    Inventory_Res_DTO toResponseDto(Inventory entity);
}

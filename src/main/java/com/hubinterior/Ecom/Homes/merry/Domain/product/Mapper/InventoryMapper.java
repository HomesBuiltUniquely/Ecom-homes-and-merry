package com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Inventory_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Inventory_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    @Mapping(source = "sku_Id", target = "sku_Id")
    @Mapping(source = "current_stock", target = "current_stock")
    @Mapping(source = "minimum_stock_level", target = "minimum_stock_level")
    @Mapping(source = "reorder_quantity", target = "reorder_quantity")
    @Mapping(source = "sourcingLogistics", target = "sourcingLogistics")
    Inventory toEntity(Inventory_Req_DTO req);

    @Mapping(source = "sku_Id", target = "sku_Id")
    @Mapping(source = "current_stock", target = "current_stock")
    @Mapping(source = "minimum_stock_level", target = "minimum_stock_level")
    @Mapping(source = "reorder_quantity", target = "reorder_quantity")
    @Mapping(source = "sourcingLogistics", target = "sourcingLogistics")
    Inventory_Res_DTO toResponseDto(Inventory entity);
}

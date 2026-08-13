package com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Inventory_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Inventory_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Inventory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    Inventory toEntity(Inventory_Req_DTO req);

    Inventory_Res_DTO toResponseDto(Inventory entity);
}

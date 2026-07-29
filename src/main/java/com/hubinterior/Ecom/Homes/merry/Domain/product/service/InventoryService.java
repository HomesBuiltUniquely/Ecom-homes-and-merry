package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper.InventoryMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Inventory_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Inventory_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryMapper mapper;

    // Temporary in-memory store until DB layer is wired up
    private final Map<String, Inventory> temp_store = new HashMap<>();

    public Inventory_Res_DTO addInventory(Inventory_Req_DTO req) {

        Inventory newInventory = mapper.toEntity(req);

        temp_store.put(newInventory.getSku_Id(), newInventory);
        temp_store.forEach((key, value) -> System.out.println(key + " : " + value));

        return mapper.toResponseDto(newInventory);
    }
}

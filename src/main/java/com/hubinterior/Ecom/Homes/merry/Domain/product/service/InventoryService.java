package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper.InventoryMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Inventory_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Inventory_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Inventory;
import com.hubinterior.Ecom.Homes.merry.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryMapper mapper;

    // Inventory is keyed by sku_Id (natural business key)
    private final Map<String, Inventory> temp_store = new HashMap<>();

    // ── CREATE ────────────────────────────────────────────────────────────────
    public Inventory_Res_DTO addInventory(Inventory_Req_DTO req) {
        Inventory inv = mapper.toEntity(req);
        temp_store.put(inv.getSku_Id(), inv);
        System.out.println("Created: " + inv);
        return mapper.toResponseDto(inv);
    }

    // ── READ ALL ──────────────────────────────────────────────────────────────
    public List<Inventory_Res_DTO> getAllInventory() {
        return temp_store.values().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // ── READ BY SKU ───────────────────────────────────────────────────────────
    public Inventory_Res_DTO getInventoryBySku(String sku_Id) {
        Inventory inv = temp_store.get(sku_Id);
        if (inv == null)
            throw new ResourceNotFoundException("Inventory not found with SKU: " + sku_Id);
        return mapper.toResponseDto(inv);
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    public Inventory_Res_DTO updateInventory(String sku_Id, Inventory_Req_DTO req) {
        if (!temp_store.containsKey(sku_Id))
            throw new ResourceNotFoundException("Inventory not found with SKU: " + sku_Id);
        Inventory updated = mapper.toEntity(req);
        temp_store.put(sku_Id, updated);
        System.out.println("Updated: " + updated);
        return mapper.toResponseDto(updated);
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    public void deleteInventory(String sku_Id) {
        if (!temp_store.containsKey(sku_Id))
            throw new ResourceNotFoundException("Inventory not found with SKU: " + sku_Id);
        temp_store.remove(sku_Id);
        System.out.println("Deleted inventory with SKU: " + sku_Id);
    }
}

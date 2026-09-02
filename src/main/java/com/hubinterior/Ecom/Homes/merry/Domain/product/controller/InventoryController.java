package com.hubinterior.Ecom.Homes.merry.Domain.product.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Inventory_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Inventory_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService service;


    @PutMapping("/updateInventory/{prod_id}/{sku_Id}")
    public ResponseEntity<Inventory_Res_DTO> updateInventory(
            @PathVariable Long prod_id,
            @Valid @RequestBody Inventory_Req_DTO req) {
        return ResponseEntity.ok(service.updateInventory(prod_id,req));
    }


}

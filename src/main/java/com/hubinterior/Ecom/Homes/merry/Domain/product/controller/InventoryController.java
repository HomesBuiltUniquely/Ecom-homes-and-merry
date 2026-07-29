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

    @PostMapping("/createInventory")
    public ResponseEntity<Inventory_Res_DTO> createInventory(
            @Valid @RequestBody Inventory_Req_DTO req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addInventory(req));
    }

    @GetMapping("/getAllInventory")
    public ResponseEntity<List<Inventory_Res_DTO>> getAllInventory() {
        return ResponseEntity.ok(service.getAllInventory());
    }

    // Inventory uses sku_Id as its natural key
    @GetMapping("/getInventory/{sku_Id}")
    public ResponseEntity<Inventory_Res_DTO> getInventoryBySku(@PathVariable String sku_Id) {
        return ResponseEntity.ok(service.getInventoryBySku(sku_Id));
    }

    @PutMapping("/updateInventory/{sku_Id}")
    public ResponseEntity<Inventory_Res_DTO> updateInventory(
            @PathVariable String sku_Id,
            @Valid @RequestBody Inventory_Req_DTO req) {
        return ResponseEntity.ok(service.updateInventory(sku_Id, req));
    }

    @DeleteMapping("/deleteInventory/{sku_Id}")
    public ResponseEntity<String> deleteInventory(@PathVariable String sku_Id) {
        service.deleteInventory(sku_Id);
        return ResponseEntity.ok("Inventory with SKU " + sku_Id + " deleted successfully.");
    }
}

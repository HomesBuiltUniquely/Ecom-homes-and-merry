package com.hubinterior.Ecom.Homes.merry.Domain.product.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Specifications_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Specifications_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.service.SpecificationsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/specifications")
@RequiredArgsConstructor
public class SpecificationsController {

    private final SpecificationsService service;

    @PostMapping("/createSpecifications/{prod_id}")
    public ResponseEntity<Specifications_Res_DTO> createSpecifications(
            @PathVariable Long prod_id,
            @Valid @RequestBody Specifications_Req_DTO req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addSpecifications(prod_id, req));
    }

    @GetMapping("/getAllSpecifications")
    public ResponseEntity<List<Specifications_Res_DTO>> getAllSpecifications() {
        return ResponseEntity.ok(service.getAllSpecifications());
    }

    @GetMapping("/getSpecifications/{prod_id}")
    public ResponseEntity<Specifications_Res_DTO> getSpecificationsById(@PathVariable Long prod_id) {
        return ResponseEntity.ok(service.getSpecificationsById(prod_id));
    }

    @PutMapping("/updateSpecifications/{prod_id}")
    public ResponseEntity<Specifications_Res_DTO> updateSpecifications(
            @PathVariable Long prod_id,
            @Valid @RequestBody Specifications_Req_DTO req) {
        return ResponseEntity.ok(service.updateSpecifications(prod_id, req));
    }

    @DeleteMapping("/deleteSpecifications/{prod_id}")
    public ResponseEntity<String> deleteSpecifications(@PathVariable Long prod_id) {
        service.deleteSpecifications(prod_id);
        return ResponseEntity.ok("Specifications for product id " + prod_id + " deleted successfully.");
    }
}

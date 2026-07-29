package com.hubinterior.Ecom.Homes.merry.Domain.product.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Pricing_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Pricing_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.service.PricingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pricing")
@RequiredArgsConstructor
public class PricingController {

    private final PricingService service;

    @PostMapping("/createPricing")
    public ResponseEntity<Pricing_Res_DTO> createPricing(
            @Valid @RequestBody Pricing_Req_DTO req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addPricing(req));
    }

    @GetMapping("/getAllPricing")
    public ResponseEntity<List<Pricing_Res_DTO>> getAllPricing() {
        return ResponseEntity.ok(service.getAllPricing());
    }

    @GetMapping("/getPricing/{price_id}")
    public ResponseEntity<Pricing_Res_DTO> getPricingById(@PathVariable Integer price_id) {
        return ResponseEntity.ok(service.getPricingById(price_id));
    }

    @PutMapping("/updatePricing/{price_id}")
    public ResponseEntity<Pricing_Res_DTO> updatePricing(
            @PathVariable Integer price_id,
            @Valid @RequestBody Pricing_Req_DTO req) {
        return ResponseEntity.ok(service.updatePricing(price_id, req));
    }

    @DeleteMapping("/deletePricing/{price_id}")
    public ResponseEntity<String> deletePricing(@PathVariable Integer price_id) {
        service.deletePricing(price_id);
        return ResponseEntity.ok("Pricing with id " + price_id + " deleted successfully.");
    }
}

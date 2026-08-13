package com.hubinterior.Ecom.Homes.merry.Domain.product.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Pricing_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Pricing_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Prod_Data_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.service.PricingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pricing")
@RequiredArgsConstructor
public class PricingController {

    private final PricingService service;

    @PutMapping("/updatePricing/{prod_id}")
    public ResponseEntity<Pricing_Res_DTO> updatePricing(
            @Valid @RequestBody Pricing_Req_DTO req , @PathVariable Long prod_id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updatePricing(req,prod_id));
    }
}

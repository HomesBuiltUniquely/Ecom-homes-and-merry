package com.hubinterior.Ecom.Homes.merry.Domain.product.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.CartProductDTOs;
import com.hubinterior.Ecom.Homes.merry.Domain.product.service.CartProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/internal")
@RequiredArgsConstructor
public class InternalProductController {

    private final CartProductService cartProductService;

    @PostMapping("/batch-summary")
    public ResponseEntity<List<CartProductDTOs.SummaryRes>> getBatchProductSummary(
            @Valid @RequestBody CartProductDTOs.BatchReq request
    ) {
        return ResponseEntity.ok(cartProductService.getBatchProductSummary(request));
    }

    @GetMapping("/{prodId}/summary")
    public ResponseEntity<CartProductDTOs.SummaryRes> getProductSummary(
            @PathVariable Long prodId
    ) {
        return cartProductService.getProductSummary(prodId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

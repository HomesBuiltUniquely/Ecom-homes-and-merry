package com.hubinterior.Ecom.Homes.merry.Domain.product.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.CartStockDTOs;
import com.hubinterior.Ecom.Homes.merry.Domain.product.service.CartInventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory/internal")
@RequiredArgsConstructor
public class InternalInventoryController {

    private final CartInventoryService cartInventoryService;

    @PostMapping("/verify-stock")
    public ResponseEntity<CartStockDTOs.VerificationRes> verifyStock(
            @Valid @RequestBody CartStockDTOs.VerificationReq request
    ) {
        return ResponseEntity.ok(cartInventoryService.verifyStock(request));
    }

    @PostMapping("/deduct-stock")
    public ResponseEntity<CartStockDTOs.DeductionRes> deductStock(
            @Valid @RequestBody CartStockDTOs.DeductionReq request
    ) {
        CartStockDTOs.DeductionRes response = cartInventoryService.deductStock(request);
        if (!response.success()) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/hold-stock")
    public ResponseEntity<CartStockDTOs.HoldRes> holdStock(
            @Valid @RequestBody CartStockDTOs.HoldReq request
    ) {
        CartStockDTOs.HoldRes response = cartInventoryService.holdStock(request);
        if (!response.success()) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/release-stock")
    public ResponseEntity<CartStockDTOs.ReleaseRes> releaseStock(
            @Valid @RequestBody CartStockDTOs.ReleaseReq request
    ) {
        return ResponseEntity.ok(cartInventoryService.releaseStock(request));
    }
}

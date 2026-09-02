package com.hubinterior.Ecom.Homes.merry.Domain.product.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Internal_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Internal_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.service.InternalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/internal")
@RequiredArgsConstructor
public class InternalController {

    private final InternalService service;

    @PutMapping("/updateInternal/{prod_id}")
    public ResponseEntity<Internal_Res_DTO> updateInternal(
            @PathVariable Long prod_id,
            @Valid @RequestBody Internal_Req_DTO req) {
        return ResponseEntity.ok(service.updateInternal(prod_id, req));
    }

}

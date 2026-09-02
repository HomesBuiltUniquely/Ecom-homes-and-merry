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


    @PutMapping("/updateSpecifications/{prod_id}")
    public ResponseEntity<Specifications_Res_DTO> updateSpecifications(
            @PathVariable Long prod_id,
            @Valid @RequestBody Specifications_Req_DTO req) {
        return ResponseEntity.ok(service.updateSpecifications(prod_id, req));
    }

}

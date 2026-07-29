package com.hubinterior.Ecom.Homes.merry.Domain.product.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Specifications_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Specifications_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.service.SpecificationsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/specifications")
@RequiredArgsConstructor
public class SpecificationsController {

    private final SpecificationsService service;

    @PostMapping("/createSpecifications")
    public ResponseEntity<Specifications_Res_DTO> addSpecifications(
            @Valid @RequestBody Specifications_Req_DTO req) {

        Specifications_Res_DTO response = service.addSpecifications(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

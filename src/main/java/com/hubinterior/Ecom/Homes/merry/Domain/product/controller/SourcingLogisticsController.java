package com.hubinterior.Ecom.Homes.merry.Domain.product.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.SourcingLogistics_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.SourcingLogistics_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.service.SourcingLogisticsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sourcing")
@RequiredArgsConstructor
public class SourcingLogisticsController {

    private final SourcingLogisticsService service;

    @PostMapping("/createSourcing")
    public ResponseEntity<SourcingLogistics_Res_DTO> addSourcingLogistics(
            @Valid @RequestBody SourcingLogistics_Req_DTO req) {

        SourcingLogistics_Res_DTO response = service.addSourcingLogistics(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

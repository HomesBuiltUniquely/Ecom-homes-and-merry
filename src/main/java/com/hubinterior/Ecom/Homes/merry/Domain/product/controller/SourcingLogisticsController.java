package com.hubinterior.Ecom.Homes.merry.Domain.product.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.SourcingLogistics_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.SourcingLogistics_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.service.SourcingLogisticsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sourcing")
@RequiredArgsConstructor
public class SourcingLogisticsController {

    private final SourcingLogisticsService service;

    @PostMapping("/createSourcing")
    public ResponseEntity<SourcingLogistics_Res_DTO> createSourcingLogistics(
            @Valid @RequestBody SourcingLogistics_Req_DTO req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addSourcingLogistics(req));
    }

    @GetMapping("/getAllSourcing")
    public ResponseEntity<List<SourcingLogistics_Res_DTO>> getAllSourcingLogistics() {
        return ResponseEntity.ok(service.getAllSourcingLogistics());
    }

    @GetMapping("/getSourcing/{sl_id}")
    public ResponseEntity<SourcingLogistics_Res_DTO> getSourcingLogisticsById(@PathVariable Integer sl_id) {
        return ResponseEntity.ok(service.getSourcingLogisticsById(sl_id));
    }

    @PutMapping("/updateSourcing/{sl_id}")
    public ResponseEntity<SourcingLogistics_Res_DTO> updateSourcingLogistics(
            @PathVariable Integer sl_id,
            @Valid @RequestBody SourcingLogistics_Req_DTO req) {
        return ResponseEntity.ok(service.updateSourcingLogistics(sl_id, req));
    }

    @DeleteMapping("/deleteSourcing/{sl_id}")
    public ResponseEntity<String> deleteSourcingLogistics(@PathVariable Integer sl_id) {
        service.deleteSourcingLogistics(sl_id);
        return ResponseEntity.ok("Sourcing record with id " + sl_id + " deleted successfully.");
    }
}

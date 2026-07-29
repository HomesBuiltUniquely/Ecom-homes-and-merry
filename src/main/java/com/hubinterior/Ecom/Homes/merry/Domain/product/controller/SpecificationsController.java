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

    @PostMapping("/createSpecifications")
    public ResponseEntity<Specifications_Res_DTO> createSpecifications(
            @Valid @RequestBody Specifications_Req_DTO req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addSpecifications(req));
    }

    @GetMapping("/getAllSpecifications")
    public ResponseEntity<List<Specifications_Res_DTO>> getAllSpecifications() {
        return ResponseEntity.ok(service.getAllSpecifications());
    }

    @GetMapping("/getSpecifications/{spec_id}")
    public ResponseEntity<Specifications_Res_DTO> getSpecificationsById(@PathVariable Integer spec_id) {
        return ResponseEntity.ok(service.getSpecificationsById(spec_id));
    }

    @PutMapping("/updateSpecifications/{spec_id}")
    public ResponseEntity<Specifications_Res_DTO> updateSpecifications(
            @PathVariable Integer spec_id,
            @Valid @RequestBody Specifications_Req_DTO req) {
        return ResponseEntity.ok(service.updateSpecifications(spec_id, req));
    }

    @DeleteMapping("/deleteSpecifications/{spec_id}")
    public ResponseEntity<String> deleteSpecifications(@PathVariable Integer spec_id) {
        service.deleteSpecifications(spec_id);
        return ResponseEntity.ok("Specifications with id " + spec_id + " deleted successfully.");
    }
}

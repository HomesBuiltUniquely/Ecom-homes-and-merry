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

    @PostMapping("/createInternal/{prod_id}")
    public ResponseEntity<Internal_Res_DTO> createInternal(
            @PathVariable Long prod_id,
            @Valid @RequestBody Internal_Req_DTO req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addInternal(prod_id, req));
    }

    @GetMapping("/getAllInternal")
    public ResponseEntity<List<Internal_Res_DTO>> getAllInternal() {
        return ResponseEntity.ok(service.getAllInternal());
    }

    @GetMapping("/getInternal/{prod_id}")
    public ResponseEntity<Internal_Res_DTO> getInternalById(@PathVariable Long prod_id) {
        return ResponseEntity.ok(service.getInternalById(prod_id));
    }

    @PutMapping("/updateInternal/{prod_id}")
    public ResponseEntity<Internal_Res_DTO> updateInternal(
            @PathVariable Long prod_id,
            @Valid @RequestBody Internal_Req_DTO req) {
        return ResponseEntity.ok(service.updateInternal(prod_id, req));
    }

    @DeleteMapping("/deleteInternal/{prod_id}")
    public ResponseEntity<String> deleteInternal(@PathVariable Long prod_id) {
        service.deleteInternal(prod_id);
        return ResponseEntity.ok("Internal record for product id " + prod_id + " deleted successfully.");
    }
}

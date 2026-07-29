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

    @PostMapping("/createInternal")
    public ResponseEntity<Internal_Res_DTO> createInternal(
            @Valid @RequestBody Internal_Req_DTO req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addInternal(req));
    }

    @GetMapping("/getAllInternal")
    public ResponseEntity<List<Internal_Res_DTO>> getAllInternal() {
        return ResponseEntity.ok(service.getAllInternal());
    }

    @GetMapping("/getInternal/{internal_id}")
    public ResponseEntity<Internal_Res_DTO> getInternalById(@PathVariable Integer internal_id) {
        return ResponseEntity.ok(service.getInternalById(internal_id));
    }

    @PutMapping("/updateInternal/{internal_id}")
    public ResponseEntity<Internal_Res_DTO> updateInternal(
            @PathVariable Integer internal_id,
            @Valid @RequestBody Internal_Req_DTO req) {
        return ResponseEntity.ok(service.updateInternal(internal_id, req));
    }

    @DeleteMapping("/deleteInternal/{internal_id}")
    public ResponseEntity<String> deleteInternal(@PathVariable Integer internal_id) {
        service.deleteInternal(internal_id);
        return ResponseEntity.ok("Internal record with id " + internal_id + " deleted successfully.");
    }
}

package com.hubinterior.Ecom.Homes.merry.Domain.product.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Prod_Data_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Prod_Data_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.service.ProdDataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProdDataController {

    private final ProdDataService service;

    @PostMapping("/createProduct")
    public ResponseEntity<Prod_Data_Res_DTO> addProduct(
            @Valid @RequestBody Prod_Data_Req_DTO req) {

        Prod_Data_Res_DTO response = service.addProduct(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

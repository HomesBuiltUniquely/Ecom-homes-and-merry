package com.hubinterior.Ecom.Homes.merry.Domain.product.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Prod_Data_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Prod_Data_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.service.ProdDataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProdDataController {

    private final ProdDataService service;

    @PostMapping("/createProduct")
    public ResponseEntity<Prod_Data_Res_DTO> createProduct(
            @Valid @RequestBody Prod_Data_Req_DTO req) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.addProduct(req));
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<Page<Prod_Data_Res_DTO>> getAllProducts(Pageable pageable) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getAllProducts(pageable));
    }

    @GetMapping("/getProduct/{prod_id}")
    public ResponseEntity<Prod_Data_Res_DTO> getProductById(
            @PathVariable Long prod_id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getProductById(prod_id));
    }

    @PutMapping("/updateProduct/{prod_id}")
    public ResponseEntity<Prod_Data_Res_DTO> updateProduct(
            @PathVariable Long prod_id,
            @Valid @RequestBody Prod_Data_Req_DTO req) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.updateProduct(prod_id, req));
    }

    @PutMapping("/updateAllProducts")
    public ResponseEntity<List<Prod_Data_Res_DTO>> updateAllProducts(
            @RequestBody Prod_Data_Req_DTO req) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.updateAllProducts(req));
    }

    @DeleteMapping("/deleteProduct/{prod_id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long prod_id) {

        service.deleteProduct(prod_id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Product with id " + prod_id + " deleted successfully.");
    }
}

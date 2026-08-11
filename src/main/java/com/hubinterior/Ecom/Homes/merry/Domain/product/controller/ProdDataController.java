package com.hubinterior.Ecom.Homes.merry.Domain.product.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Prod_Data_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Prod_Data_Req_DTO.BulkUpdateEntry;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Prod_Data_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Prod_Data_Res_DTO.BulkUpdateResult;
import com.hubinterior.Ecom.Homes.merry.Domain.product.service.ProdDataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProdDataController {

        private final ProdDataService service;

        // ── CREATE ────────────────────────────────────────────────────────────────
        @PostMapping("/createProduct")
        public ResponseEntity<Prod_Data_Res_DTO> createProduct(
                        @Valid @RequestBody Prod_Data_Req_DTO req) {

                return ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(service.addProduct(req));
        }

        // ── READ ALL ──────────────────────────────────────────────────────────────
        @GetMapping("/getAllProducts")
        public ResponseEntity<List<Prod_Data_Res_DTO>> getAllProducts() {

                return ResponseEntity
                                .status(HttpStatus.OK)
                                .body(service.getAllProducts());
        }

        // ── READ BY ID ────────────────────────────────────────────────────────────
        @GetMapping("/getProduct/{prod_id}")
        public ResponseEntity<Prod_Data_Res_DTO> getProductById(
                        @PathVariable Long prod_id) {

                return ResponseEntity
                                .status(HttpStatus.OK)
                                .body(service.getProductById(prod_id));
        }

        // ── UPDATE (single) ───────────────────────────────────────────────────────
        @PutMapping("/updateProduct/{prod_id}")
        public ResponseEntity<Prod_Data_Res_DTO> updateProduct(
                        @PathVariable Long prod_id,
                        @Valid @RequestBody Prod_Data_Req_DTO req) {

                return ResponseEntity
                                .status(HttpStatus.OK)
                                .body(service.updateProduct(prod_id, req));
        }

        // ── UPDATE (bulk) ─────────────────────────────────────────────────────────
        // Request: JSON array of { "prod_id": <id>, "data": { ...product fields... } }
        // Returns 200 OK if all succeeded, 207 Multi-Status if any item failed.
        // Failed items appear in "failedIds" as { prod_id: "error message" }.
        @PutMapping("/updateAllProducts")
        public ResponseEntity<BulkUpdateResult> updateAllProducts(
                        @Valid @RequestBody List<@Valid BulkUpdateEntry> products) {

                BulkUpdateResult result = service.updateAllProducts(products);
                HttpStatus status = result.failedIds().isEmpty()
                                ? HttpStatus.OK
                                : HttpStatus.MULTI_STATUS;
                return ResponseEntity.status(status).body(result);
        }

        // ── DELETE ────────────────────────────────────────────────────────────────
        @DeleteMapping("/deleteProduct/{prod_id}")
        public ResponseEntity<String> deleteProduct(
                        @PathVariable Long prod_id) {

                service.deleteProduct(prod_id);
                return ResponseEntity
                                .status(HttpStatus.OK)
                                .body("Product with id " + prod_id + " deleted successfully.");
        }
}

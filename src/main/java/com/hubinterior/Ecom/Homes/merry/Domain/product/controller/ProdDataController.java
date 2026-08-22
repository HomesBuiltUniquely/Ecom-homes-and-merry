package com.hubinterior.Ecom.Homes.merry.Domain.product.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Prod_Data_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Prod_Data_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.service.ProdDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @Operation(
            summary = "Get all products (paginated)",
            description = """
                    Query params: page (0-based), size, sort.
                    Valid sort fields: prod_id, offering_name, sku_id, brand, featured_offer, category.
                    Sort format: field,direction — example: offering_name,asc
                    Leave sort empty to use default: prod_id,asc
                    """
    )
    @GetMapping("/getAllProducts")
    public ResponseEntity<Page<Prod_Data_Res_DTO>> getAllProducts(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Items per page") @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Optional. Example: offering_name,asc") @RequestParam(required = false) String sort) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getAllProducts(page, size, sort));
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

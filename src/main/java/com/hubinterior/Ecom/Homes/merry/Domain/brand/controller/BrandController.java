package com.hubinterior.Ecom.Homes.merry.Domain.brand.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.brand.dto.BrandDropdownDTO;
import com.hubinterior.Ecom.Homes.merry.Domain.brand.dto.BrandReqDTO;
import com.hubinterior.Ecom.Homes.merry.Domain.brand.dto.BrandResDTO;
import com.hubinterior.Ecom.Homes.merry.Domain.brand.dto.BrandStatsResDTO;
import com.hubinterior.Ecom.Homes.merry.Domain.brand.enums.BrandStatus;
import com.hubinterior.Ecom.Homes.merry.Domain.brand.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping("/createBrand")
    public ResponseEntity<BrandResDTO> createBrand(@Valid @RequestBody BrandReqDTO req) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(brandService.createBrand(req));
    }

    @PutMapping("/updateBrand/{brandId}")
    public ResponseEntity<BrandResDTO> updateBrand(
            @PathVariable Long brandId,
            @Valid @RequestBody BrandReqDTO req
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(brandService.updateBrand(brandId, req));
    }

    @GetMapping("/getAllBrands")
    public ResponseEntity<Page<BrandResDTO>> getAllBrands(
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "status", required = false) BrandStatus status,
            @RequestParam(name = "country", required = false) String country,
            @PageableDefault(page = 0, size = 15, sort = "updatedAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(brandService.getAllBrands(search, status, country, pageable));
    }

    @GetMapping("/getBrand/{brandId}")
    public ResponseEntity<BrandResDTO> getBrandById(@PathVariable Long brandId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(brandService.getBrandById(brandId));
    }

    @GetMapping("/dropdown")
    public ResponseEntity<List<BrandDropdownDTO>> getActiveBrandsDropdown() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(brandService.getActiveBrandsDropdown());
    }

    @GetMapping("/stats")
    public ResponseEntity<BrandStatsResDTO> getBrandStats() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(brandService.getBrandStats());
    }

    @DeleteMapping("/deleteBrand/{brandId}")
    public ResponseEntity<String> deleteBrand(@PathVariable Long brandId) {
        brandService.deleteBrand(brandId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Brand with id " + brandId + " deleted successfully.");
    }
}

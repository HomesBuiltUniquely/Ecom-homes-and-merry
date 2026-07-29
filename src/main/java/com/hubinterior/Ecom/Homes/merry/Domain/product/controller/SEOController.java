package com.hubinterior.Ecom.Homes.merry.Domain.product.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.SEO_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.SEO_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.service.SEOService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seo")
@RequiredArgsConstructor
public class SEOController {

    private final SEOService service;

    @PostMapping("/createSEO")
    public ResponseEntity<SEO_Res_DTO> createSEO(
            @Valid @RequestBody SEO_Req_DTO req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addSEO(req));
    }

    @GetMapping("/getAllSEO")
    public ResponseEntity<List<SEO_Res_DTO>> getAllSEO() {
        return ResponseEntity.ok(service.getAllSEO());
    }

    // SEO uses url_slug as its natural key
    @GetMapping("/getSEO/{url_slug}")
    public ResponseEntity<SEO_Res_DTO> getSEOBySlug(@PathVariable String url_slug) {
        return ResponseEntity.ok(service.getSEOBySlug(url_slug));
    }

    @PutMapping("/updateSEO/{url_slug}")
    public ResponseEntity<SEO_Res_DTO> updateSEO(
            @PathVariable String url_slug,
            @Valid @RequestBody SEO_Req_DTO req) {
        return ResponseEntity.ok(service.updateSEO(url_slug, req));
    }

    @DeleteMapping("/deleteSEO/{url_slug}")
    public ResponseEntity<String> deleteSEO(@PathVariable String url_slug) {
        service.deleteSEO(url_slug);
        return ResponseEntity.ok("SEO record with slug '" + url_slug + "' deleted successfully.");
    }
}

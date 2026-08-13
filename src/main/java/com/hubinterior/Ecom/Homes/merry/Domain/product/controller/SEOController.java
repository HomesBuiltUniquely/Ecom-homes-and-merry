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

    @PutMapping("/updateSEO/{prod_id}")
    public ResponseEntity<SEO_Res_DTO> updateSEO(
            @PathVariable Long prod_id,
            @Valid @RequestBody SEO_Req_DTO req) {
        return ResponseEntity.ok(service.updateSEO(prod_id, req));
    }

}

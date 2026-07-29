package com.hubinterior.Ecom.Homes.merry.Domain.product.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.SEO_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.SEO_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.service.SEOService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/seo")
@RequiredArgsConstructor
public class SEOController {

    private final SEOService service;

    @PostMapping("/createSEO")
    public ResponseEntity<SEO_Res_DTO> addSEO(
            @Valid @RequestBody SEO_Req_DTO req) {

        SEO_Res_DTO response = service.addSEO(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

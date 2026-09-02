package com.hubinterior.Ecom.Homes.merry.Domain.product.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Media_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Media_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import com.hubinterior.Ecom.Homes.merry.Domain.product.service.MediaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/media")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService service;

    @PutMapping("/updateMedia/{prod_id}")
    public ResponseEntity<Media_Res_DTO> updateMedia(
            @PathVariable  Long prod_id,
            @Valid @RequestBody Media_Req_DTO req) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.updateMedia(prod_id, req));
    }
}

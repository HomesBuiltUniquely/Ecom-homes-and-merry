package com.hubinterior.Ecom.Homes.merry.Domain.product.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Media_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Media_Res_DTO;
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

    @PostMapping("/createMedia/{prod_id}")
    public ResponseEntity<Media_Res_DTO> createMedia(
            @PathVariable Long prod_id,
            @Valid @RequestBody Media_Req_DTO req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addMedia(prod_id, req));
    }

    @GetMapping("/getAllMedia")
    public ResponseEntity<List<Media_Res_DTO>> getAllMedia() {
        return ResponseEntity.ok(service.getAllMedia());
    }

    @GetMapping("/getMedia/{prod_id}")
    public ResponseEntity<Media_Res_DTO> getMediaById(@PathVariable Long prod_id) {
        return ResponseEntity.ok(service.getMediaById(prod_id));
    }

    @PutMapping("/updateMedia/{prod_id}")
    public ResponseEntity<Media_Res_DTO> updateMedia(
            @PathVariable Long prod_id,
            @Valid @RequestBody Media_Req_DTO req) {
        return ResponseEntity.ok(service.updateMedia(prod_id, req));
    }

    @DeleteMapping("/deleteMedia/{prod_id}")
    public ResponseEntity<String> deleteMedia(@PathVariable Long prod_id) {
        service.deleteMedia(prod_id);
        return ResponseEntity.ok("Media for product id " + prod_id + " deleted successfully.");
    }
}

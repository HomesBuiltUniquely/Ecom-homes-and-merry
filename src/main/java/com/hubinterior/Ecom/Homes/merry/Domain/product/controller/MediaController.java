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

    @PostMapping("/createMedia")
    public ResponseEntity<Media_Res_DTO> createMedia(
            @Valid @RequestBody Media_Req_DTO req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addMedia(req));
    }

    @GetMapping("/getAllMedia")
    public ResponseEntity<List<Media_Res_DTO>> getAllMedia() {
        return ResponseEntity.ok(service.getAllMedia());
    }

    @GetMapping("/getMedia/{media_id}")
    public ResponseEntity<Media_Res_DTO> getMediaById(@PathVariable Integer media_id) {
        return ResponseEntity.ok(service.getMediaById(media_id));
    }

    @PutMapping("/updateMedia/{media_id}")
    public ResponseEntity<Media_Res_DTO> updateMedia(
            @PathVariable Integer media_id,
            @Valid @RequestBody Media_Req_DTO req) {
        return ResponseEntity.ok(service.updateMedia(media_id, req));
    }

    @DeleteMapping("/deleteMedia/{media_id}")
    public ResponseEntity<String> deleteMedia(@PathVariable Integer media_id) {
        service.deleteMedia(media_id);
        return ResponseEntity.ok("Media with id " + media_id + " deleted successfully.");
    }
}

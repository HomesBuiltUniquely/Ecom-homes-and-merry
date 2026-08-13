package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper.MediaMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Media_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Media_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Media;
import com.hubinterior.Ecom.Homes.merry.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final MediaMapper mapper;

    // Keyed by prod_id
    private final Map<Long, Media> temp_store = new HashMap<>();

    // ── CREATE ────────────────────────────────────────────────────────────────
    public Media_Res_DTO addMedia(Long prod_id, Media_Req_DTO req) {
        Media m = mapper.toEntity(req);
        temp_store.put(prod_id, m);
        System.out.println("Created media for prod_id " + prod_id + ": " + m);
        return mapper.toResponseDto(m);
    }

    // ── READ ALL ──────────────────────────────────────────────────────────────
    public List<Media_Res_DTO> getAllMedia() {
        return temp_store.values().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // ── READ BY PROD ID ───────────────────────────────────────────────────────
    public Media_Res_DTO getMediaById(Long prod_id) {
        Media m = temp_store.get(prod_id);
        if (m == null)
            throw new ResourceNotFoundException("Media not found for product id: " + prod_id);
        return mapper.toResponseDto(m);
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    public Media_Res_DTO updateMedia(Long prod_id, Media_Req_DTO req) {
        if (!temp_store.containsKey(prod_id))
            throw new ResourceNotFoundException("Media not found for product id: " + prod_id);
        Media updated = mapper.toEntity(req);
        temp_store.put(prod_id, updated);
        System.out.println("Updated media for prod_id " + prod_id + ": " + updated);
        return mapper.toResponseDto(updated);
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    public void deleteMedia(Long prod_id) {
        if (!temp_store.containsKey(prod_id))
            throw new ResourceNotFoundException("Media not found for product id: " + prod_id);
        temp_store.remove(prod_id);
        System.out.println("Deleted media for prod_id: " + prod_id);
    }
}

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

    private final Map<Integer, Media> temp_store = new HashMap<>();
    private int id_counter = 1;

    // ── CREATE ────────────────────────────────────────────────────────────────
    public Media_Res_DTO addMedia(Media_Req_DTO req) {
        Media m = mapper.toEntity(req);
        m.setMedia_id(id_counter++);
        temp_store.put(m.getMedia_id(), m);
        System.out.println("Created: " + m);
        return mapper.toResponseDto(m);
    }

    // ── READ ALL ──────────────────────────────────────────────────────────────
    public List<Media_Res_DTO> getAllMedia() {
        return temp_store.values().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // ── READ BY ID ────────────────────────────────────────────────────────────
    public Media_Res_DTO getMediaById(Integer media_id) {
        Media m = temp_store.get(media_id);
        if (m == null)
            throw new ResourceNotFoundException("Media not found with id: " + media_id);
        return mapper.toResponseDto(m);
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    public Media_Res_DTO updateMedia(Integer media_id, Media_Req_DTO req) {
        if (!temp_store.containsKey(media_id))
            throw new ResourceNotFoundException("Media not found with id: " + media_id);
        Media updated = mapper.toEntity(req);
        updated.setMedia_id(media_id);
        temp_store.put(media_id, updated);
        System.out.println("Updated: " + updated);
        return mapper.toResponseDto(updated);
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    public void deleteMedia(Integer media_id) {
        if (!temp_store.containsKey(media_id))
            throw new ResourceNotFoundException("Media not found with id: " + media_id);
        temp_store.remove(media_id);
        System.out.println("Deleted media with id: " + media_id);
    }
}

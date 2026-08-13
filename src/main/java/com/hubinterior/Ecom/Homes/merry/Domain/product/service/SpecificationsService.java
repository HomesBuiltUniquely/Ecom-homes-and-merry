package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper.SpecificationsMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Specifications_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Specifications_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Specifications;
import com.hubinterior.Ecom.Homes.merry.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecificationsService {

    private final SpecificationsMapper mapper;

    // Keyed by prod_id
    private final Map<Long, Specifications> temp_store = new HashMap<>();

    // ── CREATE ────────────────────────────────────────────────────────────────
    public Specifications_Res_DTO addSpecifications(Long prod_id, Specifications_Req_DTO req) {
        Specifications spec = mapper.toEntity(req);
        temp_store.put(prod_id, spec);
        System.out.println("Created specifications for prod_id " + prod_id + ": " + spec);
        return mapper.toResponseDto(spec);
    }

    // ── READ ALL ──────────────────────────────────────────────────────────────
    public List<Specifications_Res_DTO> getAllSpecifications() {
        return temp_store.values().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // ── READ BY PROD ID ───────────────────────────────────────────────────────
    public Specifications_Res_DTO getSpecificationsById(Long prod_id) {
        Specifications spec = temp_store.get(prod_id);
        if (spec == null)
            throw new ResourceNotFoundException("Specifications not found for product id: " + prod_id);
        return mapper.toResponseDto(spec);
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    public Specifications_Res_DTO updateSpecifications(Long prod_id, Specifications_Req_DTO req) {
        if (!temp_store.containsKey(prod_id))
            throw new ResourceNotFoundException("Specifications not found for product id: " + prod_id);
        Specifications updated = mapper.toEntity(req);
        temp_store.put(prod_id, updated);
        System.out.println("Updated specifications for prod_id " + prod_id + ": " + updated);
        return mapper.toResponseDto(updated);
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    public void deleteSpecifications(Long prod_id) {
        if (!temp_store.containsKey(prod_id))
            throw new ResourceNotFoundException("Specifications not found for product id: " + prod_id);
        temp_store.remove(prod_id);
        System.out.println("Deleted specifications for prod_id: " + prod_id);
    }
}

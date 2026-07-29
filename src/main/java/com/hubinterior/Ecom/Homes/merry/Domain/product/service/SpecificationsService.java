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

    private final Map<Integer, Specifications> temp_store = new HashMap<>();
    private int id_counter = 1;

    // ── CREATE ────────────────────────────────────────────────────────────────
    public Specifications_Res_DTO addSpecifications(Specifications_Req_DTO req) {
        Specifications spec = mapper.toEntity(req);
        temp_store.put(id_counter++, spec);
        System.out.println("Created: " + spec);
        return mapper.toResponseDto(spec);
    }

    // ── READ ALL ──────────────────────────────────────────────────────────────
    public List<Specifications_Res_DTO> getAllSpecifications() {
        return temp_store.values().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // ── READ BY ID ────────────────────────────────────────────────────────────
    public Specifications_Res_DTO getSpecificationsById(Integer spec_id) {
        Specifications spec = temp_store.get(spec_id);
        if (spec == null)
            throw new ResourceNotFoundException("Specifications not found with id: " + spec_id);
        return mapper.toResponseDto(spec);
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    public Specifications_Res_DTO updateSpecifications(Integer spec_id, Specifications_Req_DTO req) {
        if (!temp_store.containsKey(spec_id))
            throw new ResourceNotFoundException("Specifications not found with id: " + spec_id);
        Specifications updated = mapper.toEntity(req);
        temp_store.put(spec_id, updated);
        System.out.println("Updated: " + updated);
        return mapper.toResponseDto(updated);
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    public void deleteSpecifications(Integer spec_id) {
        if (!temp_store.containsKey(spec_id))
            throw new ResourceNotFoundException("Specifications not found with id: " + spec_id);
        temp_store.remove(spec_id);
        System.out.println("Deleted specifications with id: " + spec_id);
    }
}

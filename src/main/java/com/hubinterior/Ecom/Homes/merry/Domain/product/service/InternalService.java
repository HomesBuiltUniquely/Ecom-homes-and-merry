package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper.InternalMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Internal_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Internal_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Internal;
import com.hubinterior.Ecom.Homes.merry.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InternalService {

    private final InternalMapper mapper;

    private final Map<Integer, Internal> temp_store = new HashMap<>();
    private int id_counter = 1;

    // ── CREATE ────────────────────────────────────────────────────────────────
    public Internal_Res_DTO addInternal(Internal_Req_DTO req) {
        Internal internal = mapper.toEntity(req);
        temp_store.put(id_counter++, internal);
        System.out.println("Created: " + internal);
        return mapper.toResponseDto(internal);
    }

    // ── READ ALL ──────────────────────────────────────────────────────────────
    public List<Internal_Res_DTO> getAllInternal() {
        return temp_store.values().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // ── READ BY ID ────────────────────────────────────────────────────────────
    public Internal_Res_DTO getInternalById(Integer internal_id) {
        Internal internal = temp_store.get(internal_id);
        if (internal == null)
            throw new ResourceNotFoundException("Internal record not found with id: " + internal_id);
        return mapper.toResponseDto(internal);
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    public Internal_Res_DTO updateInternal(Integer internal_id, Internal_Req_DTO req) {
        if (!temp_store.containsKey(internal_id))
            throw new ResourceNotFoundException("Internal record not found with id: " + internal_id);
        Internal updated = mapper.toEntity(req);
        temp_store.put(internal_id, updated);
        System.out.println("Updated: " + updated);
        return mapper.toResponseDto(updated);
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    public void deleteInternal(Integer internal_id) {
        if (!temp_store.containsKey(internal_id))
            throw new ResourceNotFoundException("Internal record not found with id: " + internal_id);
        temp_store.remove(internal_id);
        System.out.println("Deleted internal record with id: " + internal_id);
    }
}

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

    // Keyed by prod_id
    private final Map<Long, Internal> temp_store = new HashMap<>();

    // ── CREATE ────────────────────────────────────────────────────────────────
    public Internal_Res_DTO addInternal(Long prod_id, Internal_Req_DTO req) {
        Internal internal = mapper.toEntity(req);
        temp_store.put(prod_id, internal);
        System.out.println("Created internal for prod_id " + prod_id + ": " + internal);
        return mapper.toResponseDto(internal);
    }

    // ── READ ALL ──────────────────────────────────────────────────────────────
    public List<Internal_Res_DTO> getAllInternal() {
        return temp_store.values().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // ── READ BY PROD ID ───────────────────────────────────────────────────────
    public Internal_Res_DTO getInternalById(Long prod_id) {
        Internal internal = temp_store.get(prod_id);
        if (internal == null)
            throw new ResourceNotFoundException("Internal record not found for product id: " + prod_id);
        return mapper.toResponseDto(internal);
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    public Internal_Res_DTO updateInternal(Long prod_id, Internal_Req_DTO req) {
        if (!temp_store.containsKey(prod_id))
            throw new ResourceNotFoundException("Internal record not found for product id: " + prod_id);
        Internal updated = mapper.toEntity(req);
        temp_store.put(prod_id, updated);
        System.out.println("Updated internal for prod_id " + prod_id + ": " + updated);
        return mapper.toResponseDto(updated);
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    public void deleteInternal(Long prod_id) {
        if (!temp_store.containsKey(prod_id))
            throw new ResourceNotFoundException("Internal record not found for product id: " + prod_id);
        temp_store.remove(prod_id);
        System.out.println("Deleted internal record for prod_id: " + prod_id);
    }
}

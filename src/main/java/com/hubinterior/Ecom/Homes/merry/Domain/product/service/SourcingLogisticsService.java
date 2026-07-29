package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper.SourcingLogisticsMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.SourcingLogistics_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.SourcingLogistics_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.SourcingLogistics;
import com.hubinterior.Ecom.Homes.merry.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SourcingLogisticsService {

    private final SourcingLogisticsMapper mapper;

    private final Map<Integer, SourcingLogistics> temp_store = new HashMap<>();
    private int id_counter = 1;

    // ── CREATE ────────────────────────────────────────────────────────────────
    public SourcingLogistics_Res_DTO addSourcingLogistics(SourcingLogistics_Req_DTO req) {
        SourcingLogistics sl = mapper.toEntity(req);
        sl.setSl_id(id_counter++);
        temp_store.put(sl.getSl_id(), sl);
        System.out.println("Created: " + sl);
        return mapper.toResponseDto(sl);
    }

    // ── READ ALL ──────────────────────────────────────────────────────────────
    public List<SourcingLogistics_Res_DTO> getAllSourcingLogistics() {
        return temp_store.values().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // ── READ BY ID ────────────────────────────────────────────────────────────
    public SourcingLogistics_Res_DTO getSourcingLogisticsById(Integer sl_id) {
        SourcingLogistics sl = temp_store.get(sl_id);
        if (sl == null)
            throw new ResourceNotFoundException("Sourcing record not found with id: " + sl_id);
        return mapper.toResponseDto(sl);
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    public SourcingLogistics_Res_DTO updateSourcingLogistics(Integer sl_id, SourcingLogistics_Req_DTO req) {
        if (!temp_store.containsKey(sl_id))
            throw new ResourceNotFoundException("Sourcing record not found with id: " + sl_id);
        SourcingLogistics updated = mapper.toEntity(req);
        updated.setSl_id(sl_id);
        temp_store.put(sl_id, updated);
        System.out.println("Updated: " + updated);
        return mapper.toResponseDto(updated);
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    public void deleteSourcingLogistics(Integer sl_id) {
        if (!temp_store.containsKey(sl_id))
            throw new ResourceNotFoundException("Sourcing record not found with id: " + sl_id);
        temp_store.remove(sl_id);
        System.out.println("Deleted sourcing record with id: " + sl_id);
    }
}

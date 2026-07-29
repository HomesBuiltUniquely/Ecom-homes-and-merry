package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper.PricingMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Pricing_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Pricing_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Pricing;
import com.hubinterior.Ecom.Homes.merry.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PricingService {

    private final PricingMapper mapper;

    private final Map<Integer, Pricing> temp_store = new HashMap<>();
    private int id_counter = 1;

    private void calculateMargin(Pricing p) {
        if (p.getSelling_price() != null && p.getCost_price() != null
                && p.getSelling_price() > 0) {
            float margin = ((p.getSelling_price() - p.getCost_price())
                    / p.getSelling_price()) * 100;
            p.setMargin_percentage((int) Math.round(margin));
        }
    }

    // ── CREATE ────────────────────────────────────────────────────────────────
    public Pricing_Res_DTO addPricing(Pricing_Req_DTO req) {
        Pricing p = mapper.toEntity(req);
        p.setPrice_id(id_counter++);
        calculateMargin(p);
        temp_store.put(p.getPrice_id(), p);
        System.out.println("Created: " + p);
        return mapper.toResponseDto(p);
    }

    // ── READ ALL ──────────────────────────────────────────────────────────────
    public List<Pricing_Res_DTO> getAllPricing() {
        return temp_store.values().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // ── READ BY ID ────────────────────────────────────────────────────────────
    public Pricing_Res_DTO getPricingById(Integer price_id) {
        Pricing p = temp_store.get(price_id);
        if (p == null)
            throw new ResourceNotFoundException("Pricing not found with id: " + price_id);
        return mapper.toResponseDto(p);
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    public Pricing_Res_DTO updatePricing(Integer price_id, Pricing_Req_DTO req) {
        if (!temp_store.containsKey(price_id))
            throw new ResourceNotFoundException("Pricing not found with id: " + price_id);
        Pricing updated = mapper.toEntity(req);
        updated.setPrice_id(price_id);
        calculateMargin(updated);
        temp_store.put(price_id, updated);
        System.out.println("Updated: " + updated);
        return mapper.toResponseDto(updated);
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    public void deletePricing(Integer price_id) {
        if (!temp_store.containsKey(price_id))
            throw new ResourceNotFoundException("Pricing not found with id: " + price_id);
        temp_store.remove(price_id);
        System.out.println("Deleted pricing with id: " + price_id);
    }
}

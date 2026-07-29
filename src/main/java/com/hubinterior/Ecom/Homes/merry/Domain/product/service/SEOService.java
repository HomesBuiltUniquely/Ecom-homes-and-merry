package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper.SEOMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.SEO_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.SEO_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.SEO;
import com.hubinterior.Ecom.Homes.merry.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SEOService {

    private final SEOMapper mapper;

    // SEO is keyed by url_slug (natural business key)
    private final Map<String, SEO> temp_store = new HashMap<>();

    // ── CREATE ────────────────────────────────────────────────────────────────
    public SEO_Res_DTO addSEO(SEO_Req_DTO req) {
        SEO seo = mapper.toEntity(req);
        temp_store.put(seo.getUrl_slug(), seo);
        System.out.println("Created: " + seo);
        return mapper.toResponseDto(seo);
    }

    // ── READ ALL ──────────────────────────────────────────────────────────────
    public List<SEO_Res_DTO> getAllSEO() {
        return temp_store.values().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // ── READ BY SLUG ──────────────────────────────────────────────────────────
    public SEO_Res_DTO getSEOBySlug(String url_slug) {
        SEO seo = temp_store.get(url_slug);
        if (seo == null)
            throw new ResourceNotFoundException("SEO not found with slug: " + url_slug);
        return mapper.toResponseDto(seo);
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    public SEO_Res_DTO updateSEO(String url_slug, SEO_Req_DTO req) {
        if (!temp_store.containsKey(url_slug))
            throw new ResourceNotFoundException("SEO not found with slug: " + url_slug);
        SEO updated = mapper.toEntity(req);
        temp_store.put(url_slug, updated);
        System.out.println("Updated: " + updated);
        return mapper.toResponseDto(updated);
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    public void deleteSEO(String url_slug) {
        if (!temp_store.containsKey(url_slug))
            throw new ResourceNotFoundException("SEO not found with slug: " + url_slug);
        temp_store.remove(url_slug);
        System.out.println("Deleted SEO with slug: " + url_slug);
    }
}

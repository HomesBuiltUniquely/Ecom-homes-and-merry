package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper.ProdDataMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Prod_Data_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Prod_Data_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import com.hubinterior.Ecom.Homes.merry.Domain.product.repository.ProdDataRepository;
import com.hubinterior.Ecom.Homes.merry.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdDataService {

    private final ProdDataMapper mapper;
    private final ProdDataRepository repository;

    // ── CREATE ────────────────────────────────────────────────────────────────
    @Transactional
    public Prod_Data_Res_DTO addProduct(Prod_Data_Req_DTO req) {
        ProdData newProduct = mapper.toEntity(req);
        ProdData saved = repository.save(newProduct);
        return mapper.toResponseDto(saved);
    }

    // ── READ ALL ──────────────────────────────────────────────────────────────
    public List<Prod_Data_Res_DTO> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // ── READ BY ID ────────────────────────────────────────────────────────────
    public Prod_Data_Res_DTO getProductById(Long prod_id) {
        ProdData product = repository.findById(prod_id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + prod_id));
        return mapper.toResponseDto(product);
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    @Transactional
    public Prod_Data_Res_DTO updateProduct(Long prod_id, Prod_Data_Req_DTO req) {
        repository.findById(prod_id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + prod_id));
        ProdData updated = mapper.toEntity(req);
        updated.setProd_id(prod_id);
        return mapper.toResponseDto(repository.save(updated));
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    @Transactional
    public void deleteProduct(Long prod_id) {
        ProdData product = repository.findById(prod_id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + prod_id));
        repository.delete(product);
    }
}

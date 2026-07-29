package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper.ProdDataMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Prod_Data_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Prod_Data_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import com.hubinterior.Ecom.Homes.merry.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdDataService {

    private final ProdDataMapper mapper;

    // Temporary in-memory store until DB layer is wired up
    private final Map<Long, ProdData> temp_store = new HashMap<>();
    private long id_counter = 1;

    // ── CREATE ────────────────────────────────────────────────────────────────
    public Prod_Data_Res_DTO addProduct(Prod_Data_Req_DTO req) {

        ProdData newProduct = mapper.toEntity(req);
        newProduct.setProd_id(id_counter++);

        temp_store.put(newProduct.getProd_id(), newProduct);
        System.out.println("Created: " + newProduct);

        return mapper.toResponseDto(newProduct);
    }

    // ── READ ALL ──────────────────────────────────────────────────────────────
    public List<Prod_Data_Res_DTO> getAllProducts() {

        return temp_store.values()
                .stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // ── READ BY ID ────────────────────────────────────────────────────────────
    public Prod_Data_Res_DTO getProductById(Long prod_id) {

        ProdData product = temp_store.get(prod_id);

        if (product == null) {
            throw new ResourceNotFoundException("Product not found with id: " + prod_id);
        }

        return mapper.toResponseDto(product);
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    public Prod_Data_Res_DTO updateProduct(Long prod_id, Prod_Data_Req_DTO req) {

        if (!temp_store.containsKey(prod_id)) {
            throw new ResourceNotFoundException("Product not found with id: " + prod_id);
        }

        ProdData updatedProduct = mapper.toEntity(req);
        updatedProduct.setProd_id(prod_id); // preserve the original id

        temp_store.put(prod_id, updatedProduct);
        System.out.println("Updated: " + updatedProduct);

        return mapper.toResponseDto(updatedProduct);
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    public void deleteProduct(Long prod_id) {

        if (!temp_store.containsKey(prod_id)) {
            throw new ResourceNotFoundException("Product not found with id: " + prod_id);
        }

        temp_store.remove(prod_id);
        System.out.println("Deleted product with id: " + prod_id);
    }
}

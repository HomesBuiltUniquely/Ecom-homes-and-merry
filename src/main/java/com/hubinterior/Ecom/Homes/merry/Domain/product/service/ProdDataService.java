package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper.ProdDataMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Prod_Data_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Prod_Data_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import com.hubinterior.Ecom.Homes.merry.Domain.product.repository.ProdDataRepository;
import com.hubinterior.Ecom.Homes.merry.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.hubinterior.Ecom.Homes.merry.Common.PageableSortHelper.of;

@Service
@RequiredArgsConstructor
public class ProdDataService {

    private static final Map<String, String> ALLOWED_SORT_FIELDS = Map.of(
            "prod_id", "prod_id",
            "offering_name", "offering_name",
            "sku_id", "sku_id",
            "brand", "brand",
            "category", "category",
            "featured_offer", "featured_offer"
    );

    private final ProdDataMapper mapper;
    private final ProdDataRepository repository;

    @Transactional
    public Prod_Data_Res_DTO addProduct(Prod_Data_Req_DTO req) {
        if (req.sku_id() != null && repository.existsBySku_id(req.sku_id())) {
            throw new IllegalArgumentException("Product with SKU ID '" + req.sku_id() + "' already exists.");
        }
        ProdData newProduct = mapper.toEntity(req);
        ProdData saved = repository.save(newProduct);
        return mapper.toResponseDto(saved);
    }

    public Page<Prod_Data_Res_DTO> getAllProducts(int page, int size, String sort) {
        Pageable pageable = of(page, size, sort, ALLOWED_SORT_FIELDS, "prod_id");
        return repository.findAll(pageable)
                .map(mapper::toResponseDto);
    }

    public Prod_Data_Res_DTO getProductById(Long prod_id) {
        ProdData product = repository.findById(prod_id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + prod_id));
        return mapper.toResponseDto(product);
    }

    @Transactional
    public Prod_Data_Res_DTO updateProduct(Long prod_id, Prod_Data_Req_DTO req) {
        ProdData existing = repository.findById(prod_id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + prod_id));

        // If SKU ID is changed, ensure the new SKU ID is not already used by another product
        if (req.sku_id() != null && !req.sku_id().equals(existing.getSku_id())) {
            repository.findBySku_id(req.sku_id()).ifPresent(otherProduct -> {
                if (!otherProduct.getProd_id().equals(prod_id)) {
                    throw new IllegalArgumentException("SKU ID '" + req.sku_id() + "' is already assigned to another product (product ID: " + otherProduct.getProd_id() + ").");
                }
            });
        }

        mapper.updateEntityFromDto(req, existing);
        ProdData updated = repository.save(existing);
        return mapper.toResponseDto(updated);
    }

    @Transactional
    public List<Prod_Data_Res_DTO> updateAllProducts(Prod_Data_Req_DTO req) {
        List<ProdData> products = repository.findAll();
        if (products.isEmpty()) {
            return List.of();
        }

        for (ProdData existing : products) {
            String originalSku = existing.getSku_id();
            Long originalProdId = existing.getProd_id();

            // Update entity with provided non-null values from req
            mapper.updateEntityFromDto(req, existing);

            // Always preserve each product's original prod_id and sku_id to avoid unique constraint conflicts across products
            existing.setProd_id(originalProdId);
            existing.setSku_id(originalSku);
            if (existing.getInventory() != null) {
                existing.getInventory().setSku_Id(originalSku);
            }
        }

        List<ProdData> savedProducts = repository.saveAll(products);
        return savedProducts.stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteProduct(Long prod_id) {
        ProdData product = repository.findById(prod_id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + prod_id));
        repository.delete(product);
    }
}

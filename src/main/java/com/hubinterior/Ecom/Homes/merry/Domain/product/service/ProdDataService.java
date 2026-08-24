package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper.ProdDataMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Prod_Data_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Prod_Data_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import com.hubinterior.Ecom.Homes.merry.Domain.product.repository.ProdDataRepository;
import com.hubinterior.Ecom.Homes.merry.Exception.BusinessRuleException;
import com.hubinterior.Ecom.Homes.merry.Exception.DuplicateResourceException;
import com.hubinterior.Ecom.Homes.merry.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdDataService {

    private final ProdDataMapper mapper;
    private final ProdDataRepository repository;

    @Transactional
    public Prod_Data_Res_DTO addProduct(Prod_Data_Req_DTO req) {
        if (req.sku_id() != null && repository.existsBySku_id(req.sku_id())) {
            throw new DuplicateResourceException("Product with SKU ID '" + req.sku_id() + "' already exists.");
        }

        validatePricingBusinessRules(req);

        ProdData newProduct = mapper.toEntity(req);
        ProdData saved = repository.save(newProduct);
        return mapper.toResponseDto(saved);
    }

    public Page<Prod_Data_Res_DTO> getAllProducts(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toResponseDto);
    }

    public Prod_Data_Res_DTO getProductById(Long prodId) {
        ProdData product = repository.findById(prodId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + prodId));
        return mapper.toResponseDto(product);
    }

    @Transactional
    public Prod_Data_Res_DTO updateProduct(Long prodId, Prod_Data_Req_DTO req) {
        ProdData existing = repository.findById(prodId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + prodId));

        if (req.sku_id() != null && !req.sku_id().equals(existing.getSku_id())) {
            repository.findBySku_id(req.sku_id()).ifPresent(otherProduct -> {
                if (!otherProduct.getProdId().equals(prodId)) {
                    throw new DuplicateResourceException("SKU ID '" + req.sku_id() + "' is already assigned to another product (product ID: " + otherProduct.getProdId() + ").");
                }
            });
        }

        validatePricingBusinessRules(req);

        mapper.updateEntityFromDto(req, existing);
        ProdData updated = repository.save(existing);
        return mapper.toResponseDto(updated);
    }

    @Transactional
    public List<Prod_Data_Res_DTO> updateAllProducts(Prod_Data_Req_DTO req) {
        List<ProdData> products = repository.findAll();
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("No products exist in the catalog to perform bulk update.");
        }

        validatePricingBusinessRules(req);

        for (ProdData existing : products) {
            String originalSku = existing.getSku_id();
            Long originalProdId = existing.getProdId();

            mapper.updateEntityFromDto(req, existing);

            existing.setProdId(originalProdId);
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
    public void deleteProduct(Long prodId) {
        ProdData product = repository.findById(prodId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + prodId));
        repository.delete(product);
    }

    private void validatePricingBusinessRules(Prod_Data_Req_DTO req) {
        if (req.pricing() != null && req.pricing().getSelling_price() > 0 && req.pricing().getCost_price() > 0) {
            if (req.pricing().getSelling_price() < req.pricing().getCost_price()) {
                throw new BusinessRuleException("Selling price (" + req.pricing().getSelling_price() + ") cannot be lower than cost price (" + req.pricing().getCost_price() + ").");
            }
        }
    }
}

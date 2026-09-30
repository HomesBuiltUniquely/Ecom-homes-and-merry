package com.hubinterior.Ecom.Homes.merry.Domain.brand.service;

import com.hubinterior.Ecom.Homes.merry.Domain.brand.dto.BrandDropdownDTO;
import com.hubinterior.Ecom.Homes.merry.Domain.brand.dto.BrandReqDTO;
import com.hubinterior.Ecom.Homes.merry.Domain.brand.dto.BrandResDTO;
import com.hubinterior.Ecom.Homes.merry.Domain.brand.dto.BrandStatsResDTO;
import com.hubinterior.Ecom.Homes.merry.Domain.brand.enums.BrandStatus;
import com.hubinterior.Ecom.Homes.merry.Domain.brand.mapper.BrandMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.brand.model.Brand;
import com.hubinterior.Ecom.Homes.merry.Domain.brand.repository.BrandRepository;
import com.hubinterior.Ecom.Homes.merry.Domain.product.repository.ProdDataRepository;
import com.hubinterior.Ecom.Homes.merry.Exception.DuplicateResourceException;
import com.hubinterior.Ecom.Homes.merry.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    private final ProdDataRepository prodDataRepository;

    @Transactional
    public BrandResDTO createBrand(BrandReqDTO req) {
        if (req.code() != null && brandRepository.existsByCode(req.code())) {
            throw new DuplicateResourceException("Brand with code '" + req.code() + "' already exists.");
        }

        Brand brand = brandMapper.toEntity(req);
        Brand saved = brandRepository.save(brand);
        return brandMapper.toResponseDto(saved);
    }

    @Transactional
    public BrandResDTO updateBrand(Long brandId, BrandReqDTO req) {
        Brand existing = brandRepository.findById(brandId)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id: " + brandId));

        if (req.code() != null && !req.code().equalsIgnoreCase(existing.getCode())) {
            brandRepository.findByCode(req.code()).ifPresent(other -> {
                if (!other.getBrandId().equals(brandId)) {
                    throw new DuplicateResourceException("Brand code '" + req.code() + "' is already assigned to another brand (brand ID: " + other.getBrandId() + ").");
                }
            });
        }

        brandMapper.updateEntityFromDto(req, existing);
        Brand updated = brandRepository.save(existing);
        return brandMapper.toResponseDto(updated);
    }

    @Transactional(readOnly = true)
    public BrandResDTO getBrandById(Long brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id: " + brandId));
        return brandMapper.toResponseDto(brand);
    }

    @Transactional(readOnly = true)
    public Page<BrandResDTO> getAllBrands(String search, BrandStatus status, String country, Pageable pageable) {
        return brandRepository.findAllWithFilters(search, status, country, pageable)
                .map(brandMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public List<BrandDropdownDTO> getActiveBrandsDropdown() {
        return brandRepository.findByStatus(BrandStatus.ACTIVE)
                .stream()
                .map(brandMapper::toDropdownDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public BrandStatsResDTO getBrandStats() {
        long totalBrands = brandRepository.count();
        long activeOfferings = prodDataRepository.countByIs_published(true);
        long countriesCount = brandRepository.countDistinctCountries();
        long pendingReview = brandRepository.countByStatus(BrandStatus.PENDING_REVIEW);

        return new BrandStatsResDTO(totalBrands, activeOfferings, countriesCount, pendingReview);
    }

    @Transactional
    public void deleteBrand(Long brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id: " + brandId));
        brandRepository.delete(brand);
    }
}

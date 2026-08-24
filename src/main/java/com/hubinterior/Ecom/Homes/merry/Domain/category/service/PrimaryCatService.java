package com.hubinterior.Ecom.Homes.merry.Domain.category.service;
import com.hubinterior.Ecom.Homes.merry.Domain.category.dto.PrimaryCatReqData;
import com.hubinterior.Ecom.Homes.merry.Domain.category.dto.PrimaryCatResData;
import com.hubinterior.Ecom.Homes.merry.Domain.category.mapper.PrimaryCatMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.category.model.PrimaryCategory;
import com.hubinterior.Ecom.Homes.merry.Domain.category.repository.primaryCategoryRepo;
import com.hubinterior.Ecom.Homes.merry.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrimaryCatService {

    private final primaryCategoryRepo primaryCatRepo;
    private final PrimaryCatMapper mapper;


    @Transactional
    public PrimaryCatResData createCategory(PrimaryCatReqData req) {
        PrimaryCategory newCategory = mapper.toEntity(req);
        PrimaryCategory saved = primaryCatRepo.save(newCategory);
        return mapper.toResponseDto(saved);
    }


    public List<PrimaryCatResData> getAllCategory() {
        return primaryCatRepo.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }


    public PrimaryCatResData getCategoryById(Long primaryCategoryId) {
        PrimaryCategory product = primaryCatRepo.findById(primaryCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + primaryCategoryId));
        return mapper.toResponseDto(product);
    }


    @Transactional
    public PrimaryCatResData updateCategory(Long primaryCategoryId, PrimaryCatReqData req) {
        primaryCatRepo.findById(primaryCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + primaryCategoryId));
        PrimaryCategory updated = mapper.toEntity(req);
        updated.setPrimaryCategoryId(primaryCategoryId);
        return mapper.toResponseDto(primaryCatRepo.save(updated));
    }


    @Transactional
    public void deleteCategory(Long primaryCategoryId) {
        PrimaryCategory product = primaryCatRepo.findById(primaryCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + primaryCategoryId));
        primaryCatRepo.delete(product);
    }

}

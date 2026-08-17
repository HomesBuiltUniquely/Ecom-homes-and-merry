package com.hubinterior.Ecom.Homes.merry.Domain.category.service;

import com.hubinterior.Ecom.Homes.merry.Domain.category.dto.SecondaryCatReqData;
import com.hubinterior.Ecom.Homes.merry.Domain.category.dto.SecondaryCatResData;
import com.hubinterior.Ecom.Homes.merry.Domain.category.mapper.SecondaryCatMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.category.model.PrimaryCategory;
import com.hubinterior.Ecom.Homes.merry.Domain.category.model.SecondaryCategory;
import com.hubinterior.Ecom.Homes.merry.Domain.category.repository.primaryCategoryRepo;
import com.hubinterior.Ecom.Homes.merry.Domain.category.repository.secondaryCategoryRepo;
import com.hubinterior.Ecom.Homes.merry.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SecondaryCatService {

    private final secondaryCategoryRepo secondaryCatRepo;
    private final primaryCategoryRepo primaryCatRepo;
    private final SecondaryCatMapper mapper;

    @Transactional
    public SecondaryCatResData createCategory(Long primaryCategoryId, SecondaryCatReqData req) {
        PrimaryCategory primary = primaryCatRepo.findById(primaryCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Primary category not found with id: " + primaryCategoryId));

        SecondaryCategory entity = mapper.toEntity(req);
        entity.setPrimaryCategory(primary);
        linkSubCategories(entity);

        return mapper.toResponseDto(secondaryCatRepo.save(entity));
    }

    @Transactional
    public SecondaryCatResData createSubCategory(Long parentSecondaryCategoryId, SecondaryCatReqData req) {
        SecondaryCategory parent = secondaryCatRepo.findById(parentSecondaryCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Secondary category not found with id: " + parentSecondaryCategoryId));

        SecondaryCategory entity = mapper.toEntity(req);
        entity.setPrimaryCategory(parent.getPrimaryCategory());
        entity.setParent(parent);
        linkSubCategories(entity);

        return mapper.toResponseDto(secondaryCatRepo.save(entity));
    }

    public List<SecondaryCatResData> getAllCategories() {
        return secondaryCatRepo.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<SecondaryCatResData> getCategoriesByPrimaryId(Long primaryCategoryId) {
        primaryCatRepo.findById(primaryCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Primary category not found with id: " + primaryCategoryId));

        return secondaryCatRepo.findByPrimaryCategoryPrimaryCategoryIdAndParentIsNull(primaryCategoryId)
                .stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public SecondaryCatResData getCategoryById(Long secondaryCategoryId) {
        SecondaryCategory category = secondaryCatRepo.findById(secondaryCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Secondary category not found with id: " + secondaryCategoryId));
        return mapper.toResponseDto(category);
    }

    @Transactional
    public SecondaryCatResData updateCategory(Long secondaryCategoryId, SecondaryCatReqData req) {
        SecondaryCategory existing = secondaryCatRepo.findById(secondaryCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Secondary category not found with id: " + secondaryCategoryId));

        SecondaryCategory updated = mapper.toEntity(req);
        updated.setSecondaryCategoryId(secondaryCategoryId);
        updated.setPrimaryCategory(existing.getPrimaryCategory());
        updated.setParent(existing.getParent());
        linkSubCategories(updated);

        return mapper.toResponseDto(secondaryCatRepo.save(updated));
    }

    @Transactional
    public void deleteCategory(Long secondaryCategoryId) {
        SecondaryCategory category = secondaryCatRepo.findById(secondaryCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Secondary category not found with id: " + secondaryCategoryId));
        secondaryCatRepo.delete(category);
    }

    private void linkSubCategories(SecondaryCategory parent) {
        if (parent.getSubCategory() == null) {
            return;
        }
        parent.getSubCategory().forEach(child -> {
            child.setPrimaryCategory(parent.getPrimaryCategory());
            child.setParent(parent);
            linkSubCategories(child);
        });
    }
}

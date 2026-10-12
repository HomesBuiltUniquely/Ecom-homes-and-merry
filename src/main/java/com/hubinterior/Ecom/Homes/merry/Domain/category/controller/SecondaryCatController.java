package com.hubinterior.Ecom.Homes.merry.Domain.category.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.common.dto.MessageResponse;
import com.hubinterior.Ecom.Homes.merry.Domain.category.dto.SecondaryCatReqData;
import com.hubinterior.Ecom.Homes.merry.Domain.category.dto.SecondaryCatResData;
import com.hubinterior.Ecom.Homes.merry.Domain.category.service.SecondaryCatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/secondary-categories")
@RequiredArgsConstructor
public class SecondaryCatController {

    private final SecondaryCatService service;

    @PostMapping("/createCategory/{primaryCategoryId}")
    public ResponseEntity<MessageResponse<SecondaryCatResData>> createCategory(
            @PathVariable Long primaryCategoryId,
            @Valid @RequestBody SecondaryCatReqData req) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new MessageResponse<>(
                        req.secondaryCategoryName()
                                + " Secondary category created successfully.",
                        service.createCategory(primaryCategoryId, req)
                ));
    }

    @PostMapping("/createSubCategory/{parentSecondaryCategoryId}")
    public ResponseEntity<MessageResponse<SecondaryCatResData>> createSubCategory(
            @PathVariable Long parentSecondaryCategoryId,
            @Valid @RequestBody SecondaryCatReqData req) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new MessageResponse<>(
                        req.secondaryCategoryName()
                                + " Sub category created successfully.",
                        service.createSubCategory(parentSecondaryCategoryId, req)
                ));
    }

    @GetMapping("/getAllCategories")
    public ResponseEntity<List<SecondaryCatResData>> getAllCategories() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getAllCategories());
    }

    @GetMapping("/getCategoriesByPrimary/{primaryCategoryId}")
    public ResponseEntity<List<SecondaryCatResData>> getCategoriesByPrimaryId(
            @PathVariable Long primaryCategoryId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getCategoriesByPrimaryId(primaryCategoryId));
    }

    @GetMapping("/getCategory/{secondaryCategoryId}")
    public ResponseEntity<SecondaryCatResData> getCategoryById(
            @PathVariable Long secondaryCategoryId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getCategoryById(secondaryCategoryId));
    }

    @PutMapping("/updateCategory/{secondaryCategoryId}")
    public ResponseEntity<MessageResponse<SecondaryCatResData>> updateCategory(
            @PathVariable Long secondaryCategoryId,
            @Valid @RequestBody SecondaryCatReqData req) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MessageResponse<>(
                        req.secondaryCategoryName()
                                + " Secondary category updated successfully.",
                        service.updateCategory(secondaryCategoryId, req)
                ));
    }

    @DeleteMapping("/deleteCategory/{secondaryCategoryId}")
    public ResponseEntity<MessageResponse<Void>> deleteCategory(
            @PathVariable Long secondaryCategoryId) {
        service.deleteCategory(secondaryCategoryId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MessageResponse<>(
                        "Secondary category with id "
                                + secondaryCategoryId
                                + " deleted successfully."
                ));
    }
}
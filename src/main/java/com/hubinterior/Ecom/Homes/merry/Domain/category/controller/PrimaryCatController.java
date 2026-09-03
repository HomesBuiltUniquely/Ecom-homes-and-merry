package com.hubinterior.Ecom.Homes.merry.Domain.category.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hubinterior.Ecom.Homes.merry.Domain.category.dto.PrimaryCatReqData;
import com.hubinterior.Ecom.Homes.merry.Domain.category.dto.PrimaryCatResData;
import com.hubinterior.Ecom.Homes.merry.Domain.category.service.PrimaryCatService;
import com.hubinterior.Ecom.Homes.merry.Domain.common.dto.MessageResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class PrimaryCatController {

    private final PrimaryCatService service;

    @PostMapping("/createCategory")
    public ResponseEntity<MessageResponse<PrimaryCatResData>> createCategory(
            @Valid @RequestBody PrimaryCatReqData req) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new MessageResponse<>(
                        req.primaryCategoryName()
                                + " Primary category created successfully.",
                        service.createCategory(req)
                ));
    }

    @GetMapping("/getAllCategories")
    public ResponseEntity<List<PrimaryCatResData>> getAllCategories() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getAllCategory());
    }

    @GetMapping("/getCategory/{primaryCategoryId}")
    public ResponseEntity<PrimaryCatResData> getCategoryById(
            @PathVariable Long primaryCategoryId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getCategoryById(primaryCategoryId));
    }

    @PutMapping("/updateCategory/{primaryCategoryId}")
    public ResponseEntity<MessageResponse<PrimaryCatResData>> updateCategory(
            @PathVariable Long primaryCategoryId,
            @Valid @RequestBody PrimaryCatReqData req) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MessageResponse<>(
                        req.primaryCategoryName()
                                + " Primary category updated successfully.",
                        service.updateCategory(primaryCategoryId, req)
                ));
    }

    @DeleteMapping("/deleteCategory/{primaryCategoryId}")
    public ResponseEntity<MessageResponse<Void>> deleteCategory(
            @PathVariable Long primaryCategoryId) {
        service.deleteCategory(primaryCategoryId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MessageResponse<>(
                        "Primary category with id " + primaryCategoryId +
                                " deleted successfully."
                ));
    }
}
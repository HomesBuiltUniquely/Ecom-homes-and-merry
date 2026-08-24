package com.hubinterior.Ecom.Homes.merry.Domain.category.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.category.dto.PrimaryCatReqData;
import com.hubinterior.Ecom.Homes.merry.Domain.category.dto.PrimaryCatResData;
import com.hubinterior.Ecom.Homes.merry.Domain.category.service.PrimaryCatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class PrimaryCatController {

    private final PrimaryCatService service;

    @PostMapping("/createCategory")
    public ResponseEntity<PrimaryCatResData> createCategory(
            @Valid @RequestBody PrimaryCatReqData req) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createCategory(req));
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
    public ResponseEntity<PrimaryCatResData> updateCategory(
            @PathVariable Long primaryCategoryId,
            @Valid @RequestBody PrimaryCatReqData req) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.updateCategory(primaryCategoryId, req));
    }

    @DeleteMapping("/deleteCategory/{primaryCategoryId}")
    public ResponseEntity<String> deleteCategory(
            @PathVariable Long primaryCategoryId) {
        service.deleteCategory(primaryCategoryId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Primary category with id " + primaryCategoryId + " deleted successfully.");
    }
}

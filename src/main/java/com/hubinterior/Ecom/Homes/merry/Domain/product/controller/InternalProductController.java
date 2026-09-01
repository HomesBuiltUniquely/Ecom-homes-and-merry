package com.hubinterior.Ecom.Homes.merry.Domain.product.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.ProductBatchReqDTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.ProductSummaryInternalDTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import com.hubinterior.Ecom.Homes.merry.Domain.product.repository.ProdDataRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products/internal")
@RequiredArgsConstructor
public class InternalProductController {

    private final ProdDataRepository prodRepo;

    @PostMapping("/batch-summary")
    public ResponseEntity<List<ProductSummaryInternalDTO>> getBatchProductSummary(
            @Valid @RequestBody ProductBatchReqDTO request
    ) {
        if (request.productIds() == null || request.productIds().isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        List<ProdData> products = prodRepo.findByProdIdIn(request.productIds());

        List<ProductSummaryInternalDTO> response = products.stream()
                .map(this::mapToSummary)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{prodId}/summary")
    public ResponseEntity<ProductSummaryInternalDTO> getProductSummary(
            @PathVariable Long prodId
    ) {
        return prodRepo.findById(prodId)
                .map(prod -> ResponseEntity.ok(mapToSummary(prod)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private ProductSummaryInternalDTO mapToSummary(ProdData prod) {
        Float sellingPrice = prod.getPricing() != null ? prod.getPricing().getSelling_price() : null;
        Float costPrice = prod.getPricing() != null ? prod.getPricing().getCost_price() : null;
        Integer discount = prod.getPricing() != null ? prod.getPricing().getDiscount() : null;
        var gstRate = prod.getPricing() != null ? prod.getPricing().getGst_rate() : null;
        int currentStock = prod.getInventory() != null ? prod.getInventory().getCurrent_stock() : 0;
        String primaryImage = prod.getMedia() != null ? prod.getMedia().getPrimary_image() : null;

        return ProductSummaryInternalDTO.builder()
                .prodId(prod.getProdId())
                .sku_id(prod.getSku_id())
                .offering_name(prod.getOffering_name())
                .brand(prod.getBrand())
                .selling_price(sellingPrice)
                .cost_price(costPrice)
                .discount(discount)
                .gst_rate(gstRate)
                .current_stock(currentStock)
                .primary_image_url(primaryImage)
                .is_in_stock(currentStock > 0)
                .build();
    }
}

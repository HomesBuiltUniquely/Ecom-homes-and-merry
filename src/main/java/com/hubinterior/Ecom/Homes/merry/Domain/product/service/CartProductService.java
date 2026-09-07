package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.internal.CartProductDTOs;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import com.hubinterior.Ecom.Homes.merry.Domain.product.repository.ProdDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Enterprise service responsible for product summary projections required by the Cart domain.
 */
@Service
@RequiredArgsConstructor
public class CartProductService {

    private final ProdDataRepository prodRepo;

    @Transactional(readOnly = true)
    public List<CartProductDTOs.SummaryRes> getBatchProductSummary(CartProductDTOs.BatchReq request) {
        if (request == null || request.productIds() == null || request.productIds().isEmpty()) {
            return Collections.emptyList();
        }

        List<ProdData> products = prodRepo.findByProdIdIn(request.productIds());

        return products.stream()
                .map(this::mapToSummary)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<CartProductDTOs.SummaryRes> getProductSummary(Long prodId) {
        return prodRepo.findById(prodId)
                .map(this::mapToSummary);
    }

    private CartProductDTOs.SummaryRes mapToSummary(ProdData prod) {
        Float sellingPrice = prod.getPricing() != null ? prod.getPricing().getSelling_price() : null;
        Float costPrice = prod.getPricing() != null ? prod.getPricing().getCost_price() : null;
        Integer discount = prod.getPricing() != null ? prod.getPricing().getDiscount() : null;
        var gstRate = prod.getPricing() != null ? prod.getPricing().getGst_rate() : null;
        int currentStock = prod.getInventory() != null ? prod.getInventory().getCurrent_stock() : 0;
        String primaryImage = prod.getMedia() != null ? prod.getMedia().getPrimary_image() : null;

        return CartProductDTOs.SummaryRes.builder()
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

package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper.PricingMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Pricing_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Pricing_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Pricing;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import com.hubinterior.Ecom.Homes.merry.Domain.product.repository.ProdDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PricingService {

    private final PricingMapper mapper;
    private final ProdDataRepository prodRepo;

//    private void calculateMargin(Pricing p) {
//        if (p.getSelling_price() != null && p.getCost_price() != null
//                && p.getSelling_price() > 0) {
//            float margin = ((p.getSelling_price() - p.getCost_price())
//                    / p.getSelling_price()) * 100;
//            p.setMargin_percentage((int) Math.round(margin));
//        }
//    }

    public Pricing_Res_DTO addPricing(Pricing_Req_DTO req, Long prod_id) {
        Pricing p = mapper.toEntity(req);
        ProdData data = prodRepo.findById(prod_id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        p.setProduct();
        return mapper.toResponseDto(p);
    }

//    public List<Pricing_Res_DTO> getAllPricing() {
//        return temp_store.values().stream()
//                .map(mapper::toResponseDto)
//                .collect(Collectors.toList());
//    }
//
//
//    public Pricing_Res_DTO getPricingById(Integer price_id) {
//        Pricing p = temp_store.get(price_id);
//        if (p == null)
//            throw new ResourceNotFoundException("Pricing not found with id: " + price_id);
//        return mapper.toResponseDto(p);
//    }
//
//
//    public Pricing_Res_DTO updatePricing(Integer price_id, Pricing_Req_DTO req) {
//        if (!temp_store.containsKey(price_id))
//            throw new ResourceNotFoundException("Pricing not found with id: " + price_id);
//        Pricing updated = mapper.toEntity(req);
//        updated.setPrice_id(price_id);
//        calculateMargin(updated);
//        temp_store.put(price_id, updated);
//        System.out.println("Updated: " + updated);
//        return mapper.toResponseDto(updated);
//    }
//
//
//    public void deletePricing(Integer price_id) {
//        if (!temp_store.containsKey(price_id))
//            throw new ResourceNotFoundException("Pricing not found with id: " + price_id);
//        temp_store.remove(price_id);
//        System.out.println("Deleted pricing with id: " + price_id);
//    }
}

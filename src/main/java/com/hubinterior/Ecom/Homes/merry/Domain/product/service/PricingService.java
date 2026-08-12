package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper.PricingMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper.ProdDataMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Pricing_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Pricing_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Prod_Data_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Pricing;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import com.hubinterior.Ecom.Homes.merry.Domain.product.repository.ProdDataRepository;
import com.hubinterior.Ecom.Homes.merry.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PricingService {

    private final PricingMapper mapper;
    private final ProdDataMapper prodMapper;
    private final ProdDataRepository prodRepo;

//    private void calculateMargin(Pricing p) {
//        if (p.getSelling_price() != null && p.getCost_price() != null
//                && p.getSelling_price() > 0) {
//            float margin = ((p.getSelling_price() - p.getCost_price())
//                    / p.getSelling_price()) * 100;
//            p.setMargin_percentage((int) Math.round(margin));
//        }
//    }

    @Transactional
    public Prod_Data_Res_DTO updatePricing(Pricing_Req_DTO req, Long prod_id) {
        Pricing p = mapper.toEntity(req);
        ProdData data = prodRepo.findById(prod_id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + prod_id));
        data.setPricing(p);
        prodRepo.save(data);
        return prodMapper.toResponseDto(data);
    }


}

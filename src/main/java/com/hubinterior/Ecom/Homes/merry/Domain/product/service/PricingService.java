package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper.PricingMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Pricing_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Pricing_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Pricing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PricingService {

    private final PricingMapper mapper;

    private final Map<Integer, Pricing> temp_store = new HashMap<>();
    private int id_counter = 1;

    public Pricing_Res_DTO addPricing(Pricing_Req_DTO req) {

        Pricing newPricing = mapper.toEntity(req);
        newPricing.setPrice_id(id_counter++);

        // Calculate margin percentage: ((selling - cost) / selling) * 100
        if (newPricing.getSelling_price() != null && newPricing.getCost_price() != null) {
            float margin = ((newPricing.getSelling_price() - newPricing.getCost_price()) 
                            / newPricing.getSelling_price()) * 100;
            newPricing.setMargin_percentage((int) Math.round(margin));
        }

        temp_store.put(newPricing.getPrice_id(), newPricing);
        temp_store.forEach((key, value) ->
                System.out.println(key + " : " + value));

        return mapper.toResponseDto(newPricing);
    }
}

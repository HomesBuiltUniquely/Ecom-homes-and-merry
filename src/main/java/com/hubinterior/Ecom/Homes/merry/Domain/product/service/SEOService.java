package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper.SEOMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.SEO_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.SEO_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.SEO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SEOService {

    private final SEOMapper mapper;

    // Temporary in-memory store until DB layer is wired up
    private final Map<Integer, SEO> temp_store = new HashMap<>();
    private int id_counter = 1;

    public SEO_Res_DTO addSEO(SEO_Req_DTO req) {

        SEO newSEO = mapper.toEntity(req);

        temp_store.put(id_counter++, newSEO);
        temp_store.forEach((key, value) ->
                System.out.println(key + " : " + value));

        return mapper.toResponseDto(newSEO);
    }
}

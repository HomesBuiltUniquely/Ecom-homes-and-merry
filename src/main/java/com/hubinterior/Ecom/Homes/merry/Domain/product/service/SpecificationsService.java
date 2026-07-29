package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper.SpecificationsMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Specifications_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Specifications_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Specifications;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SpecificationsService {

    private final SpecificationsMapper mapper;

    // Temporary in-memory store until DB layer is wired up
    private final Map<Integer, Specifications> temp_store = new HashMap<>();
    private int id_counter = 1;

    public Specifications_Res_DTO addSpecifications(Specifications_Req_DTO req) {

        Specifications newSpec = mapper.toEntity(req);

        temp_store.put(id_counter++, newSpec);
        temp_store.forEach((key, value) ->
                System.out.println(key + " : " + value));

        return mapper.toResponseDto(newSpec);
    }
}

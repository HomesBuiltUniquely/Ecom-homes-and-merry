package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper.SourcingLogisticsMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.SourcingLogistics_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.SourcingLogistics_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.SourcingLogistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SourcingLogisticsService {

    private final SourcingLogisticsMapper mapper;

    // Temporary in-memory store until DB layer is wired up
    private final Map<Integer, SourcingLogistics> temp_store = new HashMap<>();
    private int id_counter = 1;

    public SourcingLogistics_Res_DTO addSourcingLogistics(SourcingLogistics_Req_DTO req) {

        SourcingLogistics newSL = mapper.toEntity(req);
        newSL.setSl_id(id_counter++);

        temp_store.put(newSL.getSl_id(), newSL);
        temp_store.forEach((key, value) ->
                System.out.println(key + " : " + value));

        return mapper.toResponseDto(newSL);
    }
}

package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper.InternalMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Internal_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Internal_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Internal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InternalService {

    private final InternalMapper mapper;

    // Temporary in-memory store until DB layer is wired up
    private final Map<Integer, Internal> temp_store = new HashMap<>();
    private int id_counter = 1;

    public Internal_Res_DTO addInternal(Internal_Req_DTO req) {

        Internal newInternal = mapper.toEntity(req);

        temp_store.put(id_counter++, newInternal);
        temp_store.forEach((key, value) ->
                System.out.println(key + " : " + value));

        return mapper.toResponseDto(newInternal);
    }
}

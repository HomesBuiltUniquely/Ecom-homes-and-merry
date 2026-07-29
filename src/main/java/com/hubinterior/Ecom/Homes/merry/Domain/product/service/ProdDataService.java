package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper.ProdDataMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Prod_Data_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Prod_Data_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProdDataService {

    private final ProdDataMapper mapper;

    // Temporary in-memory store until DB layer is wired up
    private final Map<Long, ProdData> temp_store = new HashMap<>();
    private long id_counter = 1;

    public Prod_Data_Res_DTO addProduct(Prod_Data_Req_DTO req) {

        ProdData newProduct = mapper.toEntity(req);
        newProduct.setProd_id(id_counter++);

        temp_store.put(newProduct.getProd_id(), newProduct);
        temp_store.forEach((key, value) ->
                System.out.println(key + " : " + value));

        return mapper.toResponseDto(newProduct);
    }
}

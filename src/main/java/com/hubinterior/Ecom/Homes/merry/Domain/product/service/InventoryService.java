package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper.InventoryMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Inventory_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Inventory_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Inventory;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import com.hubinterior.Ecom.Homes.merry.Domain.product.repository.ProdDataRepository;
import com.hubinterior.Ecom.Homes.merry.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryMapper mapper;
    private final ProdDataRepository prodRepo;

    public Inventory_Res_DTO updateInventory(Long prod_id,Inventory_Req_DTO req) {

        Inventory inventory  =mapper.toEntity(req);

        ProdData product=prodRepo.findById(prod_id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + prod_id));

        product.setInventory(inventory);

        return mapper.toResponseDto(inventory);
    }

}

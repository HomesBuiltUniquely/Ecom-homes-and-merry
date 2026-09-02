package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper.MediaMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Media_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Media_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Media;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import com.hubinterior.Ecom.Homes.merry.Domain.product.repository.ProdDataRepository;
import com.hubinterior.Ecom.Homes.merry.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final MediaMapper mapper;
    private final ProdDataRepository prodRepo;



    public Media_Res_DTO updateMedia(Long prod_id, Media_Req_DTO req) {

         Media media = mapper.toEntity(req);

         ProdData product= prodRepo.findById(prod_id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

         product.setMedia(media);

         return mapper.toResponseDto(media);
    }

}

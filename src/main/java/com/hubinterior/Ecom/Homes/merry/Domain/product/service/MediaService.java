package com.hubinterior.Ecom.Homes.merry.Domain.product.service;

import com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper.MediaMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Media_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Media_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Media;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final MediaMapper mapper;

    // Temporary in-memory store until DB layer is wired up
    private final Map<Integer, Media> temp_store = new HashMap<>();
    private int id_counter = 1;

    public Media_Res_DTO addMedia(Media_Req_DTO req) {

        Media newMedia = mapper.toEntity(req);
        newMedia.setMedia_id(id_counter++);

        temp_store.put(newMedia.getMedia_id(), newMedia);
        temp_store.forEach((key, value) ->
                System.out.println(key + " : " + value));

        return mapper.toResponseDto(newMedia);
    }
}

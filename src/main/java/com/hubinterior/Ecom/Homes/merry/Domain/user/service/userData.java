package com.hubinterior.Ecom.Homes.merry.Domain.user.service;

import com.hubinterior.Ecom.Homes.merry.Domain.user.Mapper.UserDatas;
import com.hubinterior.Ecom.Homes.merry.Domain.user.dto.UserDataRequest;
import com.hubinterior.Ecom.Homes.merry.Domain.user.dto.UserDataResponse;
import com.hubinterior.Ecom.Homes.merry.Domain.user.model.UserData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class userData {
    int count;
    private final UserDatas mapper;
    Map<Long,UserData> temp_store= new HashMap<>();

    public UserDataResponse CreateUser(UserDataRequest req){

        UserData newCustomer=mapper.toEntity(req);
        newCustomer.setId(10L);
        temp_store.put(newCustomer.getId(), newCustomer);
        temp_store.forEach((key,value)->
                System.out.println(key+":"+value)
                );

        return mapper.toResponseDto(newCustomer);
    }


}

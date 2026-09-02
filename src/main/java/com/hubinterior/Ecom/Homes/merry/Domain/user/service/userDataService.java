package com.hubinterior.Ecom.Homes.merry.Domain.user.service;

import com.hubinterior.Ecom.Homes.merry.Domain.user.Mapper.UserDatas;
import com.hubinterior.Ecom.Homes.merry.Domain.user.dto.UserDataRequest;
import com.hubinterior.Ecom.Homes.merry.Domain.user.dto.UserDataResponse;
import com.hubinterior.Ecom.Homes.merry.Domain.user.model.UserData;
import com.hubinterior.Ecom.Homes.merry.Domain.user.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class userDataService {
    private final UserDatas mapper;
    private final UserRepo user_repo;
    private final PasswordEncoder passwordEncoder;


    public UserDataResponse CreateUser(UserDataRequest req){
        UserData newCustomer=mapper.toEntity(req);
        newCustomer.setPassword(passwordEncoder.encode(req.password()));
        user_repo.saveAndFlush(newCustomer);
        return mapper.toResponseDto(newCustomer);
    }
}

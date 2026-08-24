package com.hubinterior.Ecom.Homes.merry.Domain.user.service;

import com.hubinterior.Ecom.Homes.merry.Domain.user.Mapper.UserDatas;
import com.hubinterior.Ecom.Homes.merry.Domain.user.dto.UserDataRequest;
import com.hubinterior.Ecom.Homes.merry.Domain.user.dto.UserDataResponse;
import com.hubinterior.Ecom.Homes.merry.Domain.user.model.UserData;
import com.hubinterior.Ecom.Homes.merry.Domain.user.repository.UserRepo;
import com.hubinterior.Ecom.Homes.merry.Exception.DuplicateResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class userDataService {

    private final UserDatas mapper;
    private final UserRepo user_repo;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDataResponse CreateUser(UserDataRequest req) {
        if (req.email() != null && user_repo.existsByEmail(req.email())) {
            throw new DuplicateResourceException("Email '" + req.email() + "' is already registered to another user.");
        }

        if (req.phone_number() != null) {
            Long parsedPhone = mapper.toPhoneNumber(req.phone_number());
            if (parsedPhone != null && user_repo.existsByPhone_number(parsedPhone)) {
                throw new DuplicateResourceException("Phone number '" + req.phone_number() + "' is already registered.");
            }
        }

        UserData newCustomer = mapper.toEntity(req);
        newCustomer.setPassword(passwordEncoder.encode(req.password()));
        user_repo.saveAndFlush(newCustomer);
        return mapper.toResponseDto(newCustomer);
    }
}

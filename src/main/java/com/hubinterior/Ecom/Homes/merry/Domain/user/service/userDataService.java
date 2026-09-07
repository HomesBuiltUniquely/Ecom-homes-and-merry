package com.hubinterior.Ecom.Homes.merry.Domain.user.service;

import com.hubinterior.Ecom.Homes.merry.Domain.user.Mapper.UserDatas;
import com.hubinterior.Ecom.Homes.merry.Domain.user.dto.UserDataRequest;
import com.hubinterior.Ecom.Homes.merry.Domain.user.dto.UserDataResponse;
import com.hubinterior.Ecom.Homes.merry.Domain.user.enums.UserRole;
import com.hubinterior.Ecom.Homes.merry.Domain.user.model.UserData;
import com.hubinterior.Ecom.Homes.merry.Domain.user.repository.UserRepo;
import com.hubinterior.Ecom.Homes.merry.Exception.DuplicateResourceException;
import com.hubinterior.Ecom.Homes.merry.Exception.ForbiddenException;
import com.hubinterior.Ecom.Homes.merry.Exception.ResourceNotFoundException;
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
        UserRole targetRole = req.role();
        if (targetRole == null) {
            targetRole = UserRole.RETAIL_CUSTOMER;
        }

        if (req.email() != null && user_repo.existsByEmail(req.email())) {
            throw new DuplicateResourceException("Email '" + req.email() + "' is already registered to another user.");
        }

        if (req.phone_number() != null) {
            Long parsedPhone = mapper.toPhoneNumber(req.phone_number());
            if (parsedPhone != null && user_repo.existsByPhone_number(parsedPhone)) {
                throw new DuplicateResourceException("Phone number '" + req.phone_number() + "' is already registered.");
            }
        }

        UserData newUser = mapper.toEntity(req);
        newUser.setRole(targetRole);
        newUser.setPassword(passwordEncoder.encode(req.password()));
        user_repo.saveAndFlush(newUser);
        return mapper.toResponseDto(newUser);
    }

    public UserDataResponse getUserById(Long userId) {
        UserData user = user_repo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        return mapper.toResponseDto(user);
    }
}

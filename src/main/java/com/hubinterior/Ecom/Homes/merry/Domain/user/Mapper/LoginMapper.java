package com.hubinterior.Ecom.Homes.merry.Domain.user.Mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.user.dto.AuthResponse;
import com.hubinterior.Ecom.Homes.merry.Domain.user.dto.LoginRequest;
import com.hubinterior.Ecom.Homes.merry.Domain.user.dto.UserDataRequest;
import com.hubinterior.Ecom.Homes.merry.Domain.user.dto.UserDataResponse;
import com.hubinterior.Ecom.Homes.merry.Domain.user.model.UserData;
import com.hubinterior.Ecom.Homes.merry.Domain.user.model.UserLogin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoginMapper {

    @Mapping(target = "logintime", expression = "java(java.time.LocalDateTime.now())")
    UserLogin toEntity(LoginRequest req);

    AuthResponse toResponseDto(AuthResponse entity);

}

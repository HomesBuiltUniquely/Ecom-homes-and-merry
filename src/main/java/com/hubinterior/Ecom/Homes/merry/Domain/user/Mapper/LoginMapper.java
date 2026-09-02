package com.hubinterior.Ecom.Homes.merry.Domain.user.Mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.user.dto.AuthResponse;
import com.hubinterior.Ecom.Homes.merry.Domain.user.dto.LoginRequest;
import com.hubinterior.Ecom.Homes.merry.Domain.user.model.UserLogin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoginMapper {

    UserLogin toEntity(LoginRequest req);

    @Mapping(target = "token", source = "token")
    @Mapping(target = "username", source = "name")
    AuthResponse toResponseDto(String token, String name);

}

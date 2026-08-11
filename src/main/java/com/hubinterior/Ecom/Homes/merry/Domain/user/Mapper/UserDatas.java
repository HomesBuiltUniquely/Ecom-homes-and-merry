package com.hubinterior.Ecom.Homes.merry.Domain.user.Mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.user.dto.UserDataRequest;
import com.hubinterior.Ecom.Homes.merry.Domain.user.dto.UserDataResponse;
import com.hubinterior.Ecom.Homes.merry.Domain.user.model.UserData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDatas {

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    UserData toEntity(UserDataRequest req);

    @Mapping(target = "full_name", expression = "java(entity.getFirst_name() + ' ' + entity.getLast_name())")
    @Mapping(target = "role", source = "role")
    UserDataResponse toResponseDto(UserData entity);

}

package com.hubinterior.Ecom.Homes.merry.Domain.user.Mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.user.dto.UserDataRequest;
import com.hubinterior.Ecom.Homes.merry.Domain.user.dto.UserDataResponse;
import com.hubinterior.Ecom.Homes.merry.Domain.user.model.UserData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserDatas {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(source = "first_name", target = "first_name")
    @Mapping(source = "last_name", target = "last_name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "pincode", target = "pincode")
    @Mapping(source = "phone_number", target = "phone_number", qualifiedByName = "toPhoneNumber")
    @Mapping(source = "brand_name", target = "brand_name")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "gst_number", target = "gst_number")
    UserData toEntity(UserDataRequest req);

    @Mapping(target = "full_name", source = ".", qualifiedByName = "toFullName")
    @Mapping(source = "role", target = "role")
    UserDataResponse toResponseDto(UserData entity);

    @Named("toFullName")
    default String toFullName(UserData entity) {
        if (entity == null) {
            return null;
        }
        String first = entity.getFirst_name() != null ? entity.getFirst_name() : "";
        String last = entity.getLast_name() != null ? entity.getLast_name() : "";
        return (first + " " + last).trim();
    }

    @Named("toPhoneNumber")
    default Long toPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return null;
        }
        String digits = phoneNumber.replaceAll("\\D", "");
        return digits.isEmpty() ? null : Long.parseLong(digits);
    }
}

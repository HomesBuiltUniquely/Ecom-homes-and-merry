package com.hubinterior.Ecom.Homes.merry.Domain.user.dto;

import com.hubinterior.Ecom.Homes.merry.Domain.user.enums.UserRole;

public record UserDataResponse(
                String full_name,
                UserRole role) {
}

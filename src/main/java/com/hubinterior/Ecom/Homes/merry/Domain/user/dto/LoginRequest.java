package com.hubinterior.Ecom.Homes.merry.Domain.user.dto;

import com.hubinterior.Ecom.Homes.merry.Domain.user.enums.UserRole;

public record LoginRequest(
                String Username,
                String Password,
                UserRole Role) {
}

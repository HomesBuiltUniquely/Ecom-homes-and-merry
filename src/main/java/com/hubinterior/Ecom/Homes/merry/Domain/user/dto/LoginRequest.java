package com.hubinterior.Ecom.Homes.merry.Domain.user.dto;

public record LoginRequest(
        String Username, String Password ,String Role
) {
}

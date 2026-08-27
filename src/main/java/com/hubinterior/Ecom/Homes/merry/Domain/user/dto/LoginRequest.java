package com.hubinterior.Ecom.Homes.merry.Domain.user.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hubinterior.Ecom.Homes.merry.Domain.user.enums.UserRole;

public record LoginRequest(
        @JsonProperty("Username")
        @JsonAlias({"username", "email", "Email"})
        String Username,

        @JsonProperty("Password")
        @JsonAlias({"password"})
        String Password,

        UserRole Role
) {}

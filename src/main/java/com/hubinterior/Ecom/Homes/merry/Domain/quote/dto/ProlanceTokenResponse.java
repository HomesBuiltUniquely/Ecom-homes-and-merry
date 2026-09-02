package com.hubinterior.Ecom.Homes.merry.Domain.quote.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProlanceTokenResponse(
        @JsonProperty("access_token") String accessToken,
        @JsonProperty("token_type") String tokenType,
        @JsonProperty("expires_in") Long expiresIn
) {}

package com.hubinterior.Ecom.Homes.merry.Domain.quote.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ProlanceLoginResponse(
        @JsonProperty("message") String message,
        @JsonProperty("status") Boolean status,
        @JsonProperty("data") List<PartnerData> data
) {
    public record PartnerData(
            @JsonProperty("partnerID") Long partnerId,
            @JsonProperty("sessionID") String sessionId
    ) {}
}

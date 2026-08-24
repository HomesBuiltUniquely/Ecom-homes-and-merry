package com.hubinterior.Ecom.Homes.merry.Exception.dto;

import com.hubinterior.Ecom.Homes.merry.Exception.ErrorCode;

import java.time.LocalDateTime;

public record ErrorResponse(
        int status,
        ErrorCode errorCode,
        String message,
        LocalDateTime timestamp
) {
}

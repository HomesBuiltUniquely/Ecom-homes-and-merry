package com.hubinterior.Ecom.Homes.merry.Exception.dto;

import com.hubinterior.Ecom.Homes.merry.Exception.ErrorCode;

import java.time.LocalDateTime;
import java.util.Map;

public record ValidationErrorResponse(
        int status,
        ErrorCode errorCode,
        String message,
        Map<String, String> errors,
        LocalDateTime timestamp
) {
}

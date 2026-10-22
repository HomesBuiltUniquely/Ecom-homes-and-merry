package com.hubinterior.Ecom.Homes.merry.Domain.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageResponse<T> {
    private String message;
    private T data;

    public MessageResponse(String message) {
        this.message = message;
        this.data = null;
    }

    public MessageResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }
}

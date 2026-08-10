package com.hubinterior.Ecom.Homes.merry.Domain.user.dto;

import com.hubinterior.Ecom.Homes.merry.Domain.user.model.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record UserDataRequest(

        @NotBlank(message = "Name cannot be empty")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        String first_name,

        @NotBlank(message = "Name cannot be empty")
        @Size(min = 1, max = 50, message = "Name must be between 2 and 50 characters")
        String last_name,

        @NotBlank(message = "Email is required")
        @Email(message = "Please provide a valid email address")
        String email,

        Long pincode,

        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be between 10 and 15 digits")
        String phone_number,

        @NotBlank(message = "Brand Name cannot be empty")
        @Size(min = 2, max = 150, message = "Name must be between 2 and 50 characters")
        String brand_name,

        String role,

        @Valid
        @NotNull(message = "Address is required")
        Address address,

        @NotNull(message = "Password is required")
        String password,

        String gst_number,

        LocalDateTime createdAt
) {
}


package com.hubinterior.Ecom.Homes.merry.Domain.user.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRole {
    ADMIN,
    ENTERPRISE,
    RETAIL_CUSTOMER,
    INTERIOR_CLIENT,
    DESIGNERS,
    DESIGN_MANAGERS,
    TDM,
    SALES_EXECUTIVE,
    SALES_MANAGER,
    SALES_ADMIN;

    @JsonCreator
    public static UserRole fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        for (UserRole role : UserRole.values()) {
            if (role.name().equalsIgnoreCase(value.trim())) {
                return role;
            }
        }
        throw new IllegalArgumentException("No enum constant " + UserRole.class.getName() + "." + value);
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}

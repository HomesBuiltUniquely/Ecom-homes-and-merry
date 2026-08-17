package com.hubinterior.Ecom.Homes.merry.Domain.user.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRole {
    ADMIN,
    CUSTOMER,
    ENTERPRISE;

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

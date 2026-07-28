package com.hubinterior.Ecom.Homes.merry.Domain.user.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class Address {

    @NotBlank
    String city;
    @NotBlank
    String state;
    @NotBlank
    String description;

    public Address(String city, String description, String state) {
        this.city = city;
        this.description = description;
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

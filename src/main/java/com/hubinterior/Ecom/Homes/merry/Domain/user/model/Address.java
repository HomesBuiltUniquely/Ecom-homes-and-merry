package com.hubinterior.Ecom.Homes.merry.Domain.user.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Embeddable
@Getter
@NoArgsConstructor
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

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

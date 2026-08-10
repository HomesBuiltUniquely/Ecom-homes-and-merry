package com.hubinterior.Ecom.Homes.merry.Domain.user.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserLogin {

    String username;
    String password;
    String role;
    LocalDate logintime;
}

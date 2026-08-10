package com.hubinterior.Ecom.Homes.merry.Domain.user.model;

import com.hubinterior.Ecom.Homes.merry.Domain.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Entity
@Table(name = "login")
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loginId")
    private Long userLoginId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    @Column(name = "logintime")
    private LocalDateTime logintime;
}
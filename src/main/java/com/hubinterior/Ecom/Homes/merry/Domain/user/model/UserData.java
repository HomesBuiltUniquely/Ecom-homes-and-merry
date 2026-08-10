package com.hubinterior.Ecom.Homes.merry.Domain.user.model;

import com.hubinterior.Ecom.Homes.merry.Domain.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor // Required by JPA/Hibernate
@AllArgsConstructor
@Builder
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uuid")
    private Long id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "password")
    private String password;
    @Column(name = "phone_number")
    private Long phone_number;
    @Column(name = "email")
    private String email;
    @Column(name = "pincode")
    private Long pincode;
    @Column(name = "brand_name")
    private String brand_name;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;
    @Embedded
    private Address address;
    @Column(name = "gst_number")
    private String gst_number;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

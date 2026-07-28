package com.hubinterior.Ecom.Homes.merry.Domain.user.model;

//import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

//@Entity
//@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor // Required by JPA/Hibernate
@AllArgsConstructor
@Builder
public class UserData {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;
    private Long phone_number;
    private String email;
    private Long pincode;
    private String brand_name;
    private String role;
    private Address address;
    private String gstNumber;
    private LocalDateTime createdAt;
}


package com.hubinterior.Ecom.Homes.merry.Domain.user.repository;

import com.hubinterior.Ecom.Homes.merry.Domain.user.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserData, Long> {

    Optional<UserData> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT COUNT(u) > 0 FROM UserData u WHERE u.phone_number = :phone_number")
    boolean existsByPhone_number(@Param("phone_number") Long phone_number);
}

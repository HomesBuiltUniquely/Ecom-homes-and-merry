package com.hubinterior.Ecom.Homes.merry.Domain.user.repository;

import com.hubinterior.Ecom.Homes.merry.Domain.user.model.UserData;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserData,Long> {

        Optional<UserData> findByEmail(String email);

}

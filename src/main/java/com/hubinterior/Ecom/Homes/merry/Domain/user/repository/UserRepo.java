package com.hubinterior.Ecom.Homes.merry.Domain.user.repository;

import com.hubinterior.Ecom.Homes.merry.Domain.user.model.UserData;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.Optional;
import java.util.function.Function;

public interface UserRepo extends JpaRepository<UserData,Long> {

        Optional<UserData> findByEmail(String email);

}

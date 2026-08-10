package com.hubinterior.Ecom.Homes.merry.Domain.user.controller;

import com.hubinterior.Ecom.Homes.merry.Common.JwtUtil;
import com.hubinterior.Ecom.Homes.merry.Domain.user.dto.UserDataRequest;
import com.hubinterior.Ecom.Homes.merry.Domain.user.dto.UserDataResponse;
import com.hubinterior.Ecom.Homes.merry.Domain.user.service.userDataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    //service class
    private final userDataService data;

    @PostMapping("/CreateUser")
    public ResponseEntity<UserDataResponse> createUser(
            @Valid @RequestBody UserDataRequest reqUser)
    {
        UserDataResponse userResp= data.CreateUser(reqUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResp);
    }


}

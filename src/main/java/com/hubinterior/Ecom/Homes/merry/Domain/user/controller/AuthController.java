package com.hubinterior.Ecom.Homes.merry.Domain.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hubinterior.Ecom.Homes.merry.Common.JwtUtil;
import com.hubinterior.Ecom.Homes.merry.Domain.common.dto.MessageResponse;
import com.hubinterior.Ecom.Homes.merry.Domain.user.Mapper.LoginMapper;
import com.hubinterior.Ecom.Homes.merry.Domain.user.dto.AuthResponse;
import com.hubinterior.Ecom.Homes.merry.Domain.user.dto.LoginRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final LoginMapper loginmapper;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,LoginMapper loginMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.loginmapper= loginMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<MessageResponse<AuthResponse>> login(@RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.Username(), request.Password())
        );


        String token = jwtUtil.generateToken(request.Username());

        return ResponseEntity.ok(new MessageResponse<>("Login successful.", loginmapper.toResponseDto(token, request.Username())));
    }
}
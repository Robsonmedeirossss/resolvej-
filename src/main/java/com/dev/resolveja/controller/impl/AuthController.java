package com.dev.resolveja.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dev.resolveja.dto.request.LoginRequest;
import com.dev.resolveja.dto.request.RegisterRequest;
import com.dev.resolveja.dto.response.LoginResponse;
import com.dev.resolveja.dto.response.RegisterResponse;
import com.dev.resolveja.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController { 

    private final AuthService authService;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {
        return this.authService.register(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return this.authService.login(request);
    }

}

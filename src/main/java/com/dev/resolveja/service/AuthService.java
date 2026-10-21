package com.dev.resolveja.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dev.resolveja.domain.User;
import com.dev.resolveja.dto.mapper.UserMapper;
import com.dev.resolveja.dto.request.LoginRequest;
import com.dev.resolveja.dto.request.RegisterRequest;
import com.dev.resolveja.dto.response.LoginResponse;
import com.dev.resolveja.dto.response.RegisterResponse;
import com.dev.resolveja.security.CustomUserDetails;
import com.dev.resolveja.security.TokenService;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public RegisterResponse register(RegisterRequest request) {

        Optional<User> user = this.userService.findByEmail(request.email());

        if(user.isPresent()) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Email já cadastrado"
            );
        }

        User newUser = UserMapper.toEntity(request, passwordEncoder.encode(request.password()));

        return UserMapper.toResponse(
            this.userService.save(newUser)
        );

    }

    public LoginResponse login(LoginRequest request) {

        UsernamePasswordAuthenticationToken usernameAndPassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        var authenticationManager = this.authenticationManager.authenticate(usernameAndPassword);
        CustomUserDetails customUser = (CustomUserDetails) authenticationManager.getPrincipal();
        String accessToken = this.tokenService.generateToken(customUser.getUser());

        return new LoginResponse(accessToken);
    }
    
}

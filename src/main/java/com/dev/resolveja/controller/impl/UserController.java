package com.dev.resolveja.controller.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dev.resolveja.dto.request.RegisterRequest;
import com.dev.resolveja.dto.request.update.UserUpdateRequest;
import com.dev.resolveja.dto.response.RegisterResponse;
import com.dev.resolveja.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping 
    @ResponseStatus(HttpStatus.OK)
    public List<RegisterResponse> findAll(
        @RequestParam(defaultValue = "0", required = false) int page,
        @RequestParam(defaultValue = "10", required = false) int size,
        @RequestParam(defaultValue = "createdAt", required = false) String orderBy,
        @RequestParam(defaultValue = "DESC", required = false) String direction
    ) {
        return this.userService.findAll(page, size, orderBy, direction);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegisterResponse findById(@PathVariable Long id) {
        return this.userService.findById(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse create(@Valid @RequestBody RegisterRequest request) {
        return this.userService.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegisterResponse updateById(
        @PathVariable Long id,
        @Valid @RequestBody UserUpdateRequest request
    ) {
        return this.userService.updateById(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        this.userService.deleteById(id);
    }
}

package com.dev.resolveja.controller.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dev.resolveja.dto.request.ServiceRequest;
import com.dev.resolveja.dto.request.update.ServiceUpdateRequest;
import com.dev.resolveja.dto.response.ServiceResponse;
import com.dev.resolveja.service.ServiceManager;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping("/v1/services")
public class ServiceController {
    
    private final ServiceManager serviceManager;

    @GetMapping 
    @ResponseStatus(HttpStatus.OK)
    public List<ServiceResponse> findAll(
        @RequestParam(defaultValue = "0", required = false) int page,
        @RequestParam(defaultValue = "10", required = false) int size,
        @RequestParam(defaultValue = "createdAt", required = false) String orderBy,
        @RequestParam(defaultValue = "DESC", required = false) String direction
    ) {
        return this.serviceManager.findAll(page, size, orderBy, direction);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) 
    public ServiceResponse findById(@PathVariable Long id) {
        return this.serviceManager.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceResponse create(@Valid @RequestBody ServiceRequest request) {
        return this.serviceManager.create(request);
    } 
 
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServiceResponse updateById(
        @PathVariable  Long id,
        @Valid @RequestBody ServiceUpdateRequest request
    ) {
        return this.serviceManager.updateById(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        this.serviceManager.deleteById(id);
    }
    

}

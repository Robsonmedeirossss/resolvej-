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

import com.dev.resolveja.dto.request.ServiceSolicitationRequest;
import com.dev.resolveja.dto.request.update.SolicitationStatusRequest;
import com.dev.resolveja.dto.response.RatingResponse;
import com.dev.resolveja.dto.response.ServiceSolicitationResponse;
import com.dev.resolveja.service.RatingService;
import com.dev.resolveja.service.ServiceSolicitationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping("v1/solicitations") 
public class ServiceSolicitationController {
    
    private final ServiceSolicitationService serviceSolicitationService;
    private final RatingService ratingService;

    @GetMapping 
    @ResponseStatus(HttpStatus.OK)
    public List<ServiceSolicitationResponse> findAll(
        @RequestParam(defaultValue = "0", required = false) int page, 
        @RequestParam(defaultValue = "10", required = false) int size, 
        @RequestParam(defaultValue = "createdAt", required = false) String orderBy, 
        @RequestParam(defaultValue = "DESC", required = false) String direction 
    ) {
        return this.serviceSolicitationService.findAll(page, size, orderBy, direction);
    }

    @GetMapping("/customers/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ServiceSolicitationResponse> findAllByProviderId(
        @PathVariable Long customerId,
        @RequestParam(defaultValue = "0", required = false) int page, 
        @RequestParam(defaultValue = "10", required = false) int size, 
        @RequestParam(defaultValue = "createdAt", required = false) String orderBy, 
        @RequestParam(defaultValue = "DESC", required = false) String direction 
        ) {
        return this.serviceSolicitationService.findAllByProviderId(customerId, page, size, orderBy, direction);
    }
    

    @GetMapping("/providers/{providerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ServiceSolicitationResponse> findAllByCustomerId(
        @PathVariable Long providerId,
        @RequestParam(defaultValue = "0", required = false) int page, 
        @RequestParam(defaultValue = "10", required = false) int size, 
        @RequestParam(defaultValue = "createdAt", required = false) String orderBy, 
        @RequestParam(defaultValue = "DESC", required = false) String direction 
    ) {
        return this.serviceSolicitationService.findAllByCustomerId(providerId, page, size, orderBy, direction);   
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServiceSolicitationResponse findById(@PathVariable Long id) {
        return this.serviceSolicitationService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceSolicitationResponse create(@Valid @RequestBody ServiceSolicitationRequest request) {
        return this.serviceSolicitationService.create(request);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServiceSolicitationResponse changeStatusById(
        @PathVariable Long solicitationId,
        @Valid @RequestBody SolicitationStatusRequest request
    ) {
        return this.serviceSolicitationService.changeStatus(solicitationId, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        this.serviceSolicitationService.deleteById(id);
    }

    @GetMapping("/provider/{providerId}/ratings")
    @ResponseStatus(HttpStatus.OK)
    public List<RatingResponse> findAllRatingsByProvider(@PathVariable Long providerId) {
        return this.ratingService.findAllRatingsByProviderId(providerId);
    }

    @GetMapping("/ratings")
    @ResponseStatus(HttpStatus.OK)
    public List<RatingResponse> findAllRatings(
        @RequestParam(defaultValue = "0", required = false) int page, 
        @RequestParam(defaultValue = "10", required = false) int size, 
        @RequestParam(defaultValue = "createdAt", required = false) String orderBy, 
        @RequestParam(defaultValue = "DESC", required = false) int direction 
    ) {
        return this.ratingService.findAll(page, size, orderBy, orderBy);
    } 

    @DeleteMapping("/provider/{providerId}/ratings/{ratingId}")
    public void deleteRatingById(@PathVariable Long ratingId) {
        this.ratingService.deleteById(ratingId);
    }

}

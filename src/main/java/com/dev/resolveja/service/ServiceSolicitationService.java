package com.dev.resolveja.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.dev.resolveja.domain.ProviderService;
import com.dev.resolveja.domain.ServiceSolicitation;
import com.dev.resolveja.domain.User;
import com.dev.resolveja.dto.mapper.ServiceSolicitationMapper;
import com.dev.resolveja.dto.request.ServiceSolicitationRequest;
import com.dev.resolveja.dto.request.update.SolicitationStatusRequest;
import com.dev.resolveja.dto.response.ServiceSolicitationResponse;
import com.dev.resolveja.repository.ServiceSolicitationRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor 
public class ServiceSolicitationService {

    private final ServiceSolicitationRepository serviceSolicitationRepository;
    private final UserService userService;
    private final ProviderServiceManager providerServiceManager;

    public List<ServiceSolicitationResponse> findAll(int page, int size, String orderBy, String direction) {
        Sort sort = Sort.by(
            Direction.fromString(direction),
            orderBy
        );

        Pageable pageable = PageRequest.of(page, size, sort);

        return this.serviceSolicitationRepository.findAll(pageable)
            .stream()
            .map(ServiceSolicitationMapper::toResponse)
            .toList();
    }

    public ServiceSolicitationResponse findById(Long id) {
        return ServiceSolicitationMapper.toResponse(
            this.findEntityById(id)
        );
    }

    public List<ServiceSolicitationResponse> findAllByCustomerId(Long customerId, int page, int size, String orderBy, String direction) {
        User customer = this.userService.findEntityById(customerId, "customer");

        Sort sort = Sort.by(
            Direction.fromString(direction),
            orderBy
        );

        Pageable pageable = PageRequest.of(page, size, sort);

        return this.serviceSolicitationRepository.findAllByCustomerId(customer.getId(), pageable)
            .map(ServiceSolicitationMapper::toResponse)
            .getContent();

    }

    public List<ServiceSolicitationResponse> findAllByProviderId(Long providerId, int page, int size, String orderBy, String direction) {
        User provider = this.userService.findEntityById(providerId, "provider");

        Sort sort = Sort.by(
            Direction.fromString(direction),
            orderBy
        );

        Pageable pageable = PageRequest.of(page, size, sort);

        return this.serviceSolicitationRepository.findAllByProviderId(provider.getId(), pageable)
            .map(ServiceSolicitationMapper::toResponse)
            .getContent();

    }

    @Transactional 
    public ServiceSolicitationResponse create(ServiceSolicitationRequest request) {
        User customer = this.userService.findEntityById(request.customerId(), "customer");
        ProviderService providerService = this.providerServiceManager.findEntityById(request.providerServiceId());

        ServiceSolicitation serviceSolicitation = ServiceSolicitationMapper.toEntity(request, customer, providerService);

        return ServiceSolicitationMapper.toResponse(
            this.serviceSolicitationRepository.save(serviceSolicitation)
        );
    }

    public ServiceSolicitationResponse changeStatus(Long id, SolicitationStatusRequest request) {
        ServiceSolicitation solicitation = this.findEntityById(id);

        if(!solicitation.getStatus().canTransiction(request.status())) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                String.format("Não é possível mudar o status de %s para %s, somente para: %s",
                solicitation.getStatus(), request.status(), solicitation.getStatus().getAllowedTransictions()
            ));
        }

        solicitation.setStatus(request.status());

        return ServiceSolicitationMapper.toResponse(
            this.serviceSolicitationRepository.save(solicitation)
        );
    }

    public void deleteById(Long id) {
        this.findEntityById(id);
        this.serviceSolicitationRepository.deleteById(id);
    }

    protected ServiceSolicitation findEntityById(Long id) {
        return this.serviceSolicitationRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Nenhuma serviço solicitado para o id %d", id)
            ));
    }
    
}

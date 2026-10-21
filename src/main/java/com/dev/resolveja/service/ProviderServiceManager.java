package com.dev.resolveja.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.dev.resolveja.domain.ProviderService;
import com.dev.resolveja.domain.Service;
import com.dev.resolveja.domain.User;
import com.dev.resolveja.dto.mapper.ProviderServiceMapper;
import com.dev.resolveja.dto.request.ProviderServiceRequest;
import com.dev.resolveja.dto.request.update.ProviderServiceUpdateRequest;
import com.dev.resolveja.dto.response.ProviderServiceResponse;
import com.dev.resolveja.repository.ProviderServiceRepository;

import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor 
public class ProviderServiceManager {
    
    private final ProviderServiceRepository providerServiceRepository;
    private final UserService userService;
    private final ServiceManager serviceManager;

    public List<ProviderServiceResponse> findAll(int page, int size, String orderBy, String direction) {
        Sort sort = Sort.by(
            Direction.fromString(direction),
            orderBy
        );

        Pageable pageable = PageRequest.of(page, size, sort);

        return this.providerServiceRepository.findAll(pageable)
            .stream()
            .map(ProviderServiceMapper::toResponse)
            .toList();
    }

    public List<ProviderServiceResponse> findAllByProviderId(Long providerId, int page, int size, String orderBy, String direction) {
        
        User provider = this.userService.findEntityById(providerId, "provider");
        
        Sort sort = Sort.by(
            Direction.fromString(direction),
            orderBy
        );

        Pageable pageable = PageRequest.of(page, size, sort);

        return this.providerServiceRepository.findAllByProviderId(provider.getId(), pageable)
            .map(ProviderServiceMapper::toResponse)
            .toList();
    }

    public ProviderServiceResponse findById(Long id) {
        return ProviderServiceMapper.toResponse(
            this.findEntityById(id)
        );
    }

    public ProviderServiceResponse create(ProviderServiceRequest request) {
        User provider = this.userService.findEntityById(request.providerId(), "provider");
        Service service = this.serviceManager.findEntityById(request.serviceId());

        ProviderService providerService = ProviderServiceMapper.toEntity(request, service, provider);

        return ProviderServiceMapper.toResponse(
            this.providerServiceRepository.save(providerService)
        );
    }

    public ProviderServiceResponse updateById(Long id, ProviderServiceUpdateRequest request) {
        ProviderService providerService = this.findEntityById(id);

        providerService = request.merge(providerService);
        
        return ProviderServiceMapper.toResponse(
            this.providerServiceRepository.save(providerService)
        );
    }

    public void deleteById(Long id) {
        this.findEntityById(id);
        this.providerServiceRepository.deleteById(id);
    }

    protected ProviderService findEntityById(Long id) {
        return this.providerServiceRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Nenhum serviço de provedor encontrado para o id %d", id)
            ));
    }
}

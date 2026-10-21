package com.dev.resolveja.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.dev.resolveja.domain.Service;
import com.dev.resolveja.dto.mapper.ServiceMapper;
import com.dev.resolveja.dto.request.ServiceRequest;
import com.dev.resolveja.dto.request.update.ServiceUpdateRequest;
import com.dev.resolveja.dto.response.ServiceResponse;
import com.dev.resolveja.repository.ServiceRepository;

import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor 
public class ServiceManager {

    private final ServiceRepository serviceRepository;

    public List<ServiceResponse> findAll(int page, int size, String orderBy, String direction) {

        Sort sort = Sort.by(
            Sort.Direction.fromString(direction),
            orderBy
        );
        Pageable pageable = PageRequest.of(page, size, sort);

        return this.serviceRepository.findAll(pageable)
            .stream()
            .map(ServiceMapper::toResponse)
            .toList();
    }

    public ServiceResponse findById(Long id) {
        return ServiceMapper.toResponse(
            this.findEntityById(id)
        );
    }

    public ServiceResponse create(ServiceRequest request) {

        if(this.findByName(request.name()).isPresent()) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                 "Já existe um serviço com esse nome cadastrado"
            );
        }

        return ServiceMapper.toResponse(
            this.serviceRepository.save(
                ServiceMapper.toEntity(request)
            )
        );
    }

    public ServiceResponse updateById(Long id, ServiceUpdateRequest request) {
        Service service = this.findEntityById(id);
        service = request.merge(service);

        return ServiceMapper.toResponse(
            this.serviceRepository.save(service)
        );
    }

    public void deleteById(Long id) {
        this.findEntityById(id);
        this.serviceRepository.deleteById(id);
    }

    protected  Service findEntityById(Long id) {
        return this.serviceRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Nenhuma serviço encontrado para o id %d", id)
            )
        );
    }

    private Optional<Service> findByName(String name) {
        return this.serviceRepository.findByName(name);
    }
}
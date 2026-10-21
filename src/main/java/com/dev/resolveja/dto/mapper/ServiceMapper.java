package com.dev.resolveja.dto.mapper;


import com.dev.resolveja.domain.Service;
import com.dev.resolveja.dto.request.ServiceRequest;
import com.dev.resolveja.dto.response.ServiceResponse;

public class ServiceMapper {
    public static Service toEntity(ServiceRequest request) {
        return Service.builder()
            .name(request.name())
            .description(request.description())
            .active(request.active() != null ? request.active() : true)
        .build();
    }

    public static ServiceResponse toResponse(Service service) {
        return ServiceResponse.builder()
            .id(service.getId())
            .name(service.getName())
            .description(service.getDescription())
            .iconUrl(service.getIconUrl())
            .active(service.getActive())
            .createdAt(service.getCreatedAt())
            .updatedAt(service.getUpdatedAt())
        .build();
    }

}

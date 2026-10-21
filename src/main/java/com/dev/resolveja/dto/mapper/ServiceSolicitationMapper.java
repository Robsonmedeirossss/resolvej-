package com.dev.resolveja.dto.mapper;

import com.dev.resolveja.domain.ProviderService;
import com.dev.resolveja.domain.ServiceSolicitation;
import com.dev.resolveja.domain.User;
import com.dev.resolveja.domain.enums.SolicitationStatus;
import com.dev.resolveja.dto.request.ServiceSolicitationRequest;
import com.dev.resolveja.dto.response.ServiceSolicitationResponse;

public class ServiceSolicitationMapper {
    
    public static ServiceSolicitation toEntity(
        ServiceSolicitationRequest request,
        User customer,
        ProviderService providerService
    ) {
        return ServiceSolicitation.builder()
            .customer(customer)
            .providerService(providerService)
            .status(SolicitationStatus.PENDING)
            .price(request.price())
            .scheduledAt(request.scheduledAt())
        .build();
    }

    public static ServiceSolicitationResponse toResponse(ServiceSolicitation serviceSolicitation) {
        return ServiceSolicitationResponse.builder()
            .id(serviceSolicitation.getId())
            .customerId(serviceSolicitation.getCustomer().getId())
            .providerServiceId(serviceSolicitation.getProviderService().getId())
            .status(serviceSolicitation.getStatus())
            .price(serviceSolicitation.getPrice())
            .scheduledAt(serviceSolicitation.getScheduledAt())
            .completedAt(serviceSolicitation.getCompletedAt())
            .createdAt(serviceSolicitation.getCreatedAt())
            .updatedAt(serviceSolicitation.getUpdatedAt())
        .build();
    }
}

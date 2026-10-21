package com.dev.resolveja.dto.mapper;

import com.dev.resolveja.domain.ProviderService;
import com.dev.resolveja.domain.Service;
import com.dev.resolveja.domain.User;
import com.dev.resolveja.dto.request.ProviderServiceRequest;
import com.dev.resolveja.dto.response.ProviderServiceResponse;

public class ProviderServiceMapper {
    
      public static ProviderService toEntity(ProviderServiceRequest request, Service service, User provider) {
        return ProviderService.builder()
            .name(request.name())
            .description(request.description())
            .active(request.active() != null ? request.active() : true)
            .price(request.price())
            .service(service)
            .provider(provider)
        .build();
    }

    public static ProviderServiceResponse toResponse(ProviderService providerService) {

        return ProviderServiceResponse.builder()
            .id(providerService.getId())
            .name(providerService.getName())
            .description(providerService.getDescription())
            .active(providerService.getActive())
            .price(providerService.getPrice())
            .serviceId(providerService.getService().getId())
            .providerId(providerService.getProvider().getId())
            .createdAt(providerService.getCreatedAt())
            .updatedAt(providerService.getUpdatedAt())
        .build();
    }

}

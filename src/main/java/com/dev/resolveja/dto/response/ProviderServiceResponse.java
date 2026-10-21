package com.dev.resolveja.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;

@Builder 
public record ProviderServiceResponse(
    Long id,
    String name, 
    String description,
    Boolean active,
    BigDecimal price,
    Long serviceId,
    Long providerId,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}

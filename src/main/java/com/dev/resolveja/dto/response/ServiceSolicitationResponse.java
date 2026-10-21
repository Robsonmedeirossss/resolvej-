package com.dev.resolveja.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.dev.resolveja.domain.enums.SolicitationStatus;

import lombok.Builder;

@Builder 
public record ServiceSolicitationResponse(
    Long id,
    Long customerId,
    Long providerServiceId,
    SolicitationStatus status,
    BigDecimal price,
    LocalDateTime scheduledAt,
    LocalDateTime completedAt,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}

package com.dev.resolveja.dto.response;

import java.time.LocalDateTime;

import com.dev.resolveja.domain.enums.RatingStatus;

import lombok.Builder;

@Builder 
public record RatingResponse(
    Long id,
    Short note,
    String comment,
    RatingStatus status,
    Long serviceSolicitationId,
    LocalDateTime createdAt
) {}

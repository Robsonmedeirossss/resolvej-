package com.dev.resolveja.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder 
public record ServiceResponse(
    Long id,
    String name,
    String description,
    String iconUrl,
    Boolean active,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}

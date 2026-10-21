package com.dev.resolveja.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.dev.resolveja.domain.enums.Role;

import lombok.Builder;

@Builder 
public record RegisterResponse(
    Long id,
    String name,
    String email,
    String imageUrl,
    LocalDate birthDate,
    Boolean active,
    Role role,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}

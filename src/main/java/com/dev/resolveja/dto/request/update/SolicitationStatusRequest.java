package com.dev.resolveja.dto.request.update;

import com.dev.resolveja.domain.enums.SolicitationStatus;

import jakarta.validation.constraints.NotNull;

public record SolicitationStatusRequest(
    @NotNull(message = "Campo status é obrigatório")
    SolicitationStatus status
) {}

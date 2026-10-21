package com.dev.resolveja.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ServiceSolicitationRequest(
    @NotNull(message = "Campo customerId não pode ser nulo")
    @Positive(message = "Campo customerId deve ser maior que zero")
    Long customerId,

    @NotNull(message = "Campo providerServiceId não pode ser nulo")
    @Positive(message = "Campo providerServiceId deve ser maior que zero")
    Long providerServiceId,

    @NotNull(message = "Campo price não pode ser nulo")
    @DecimalMin(value = "0.00", message = "Campo price não pode ser negativo")
    @Digits(integer = 5, fraction = 2, message = "Campo price deve ter no máximo 5 dígitos inteiros e 2 decimais")
    BigDecimal price,
    
    @NotNull(message = "Campo scheduledAt não pode ser nulo")
    LocalDateTime scheduledAt
) {}

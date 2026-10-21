package com.dev.resolveja.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProviderServiceRequest(
    @NotBlank(message = "Campo name não pode ser vazio")
    @Size(max = 250, message = "Campo name deve ter no máximo 250 caracteres")
    String name,

    @Size(max = 1500, message = "Campo description deve ter no máximo 1500 caracteres")
    String description,

    Boolean active,

    @NotNull(message = "Campo price não pode ser nulo")
    @DecimalMin(value = "0.00", message = "Campo price não pode ser negativo")
    @Digits(integer = 5, fraction = 2, message = "Campo price deve ter no máximo 5 dígitos inteiros e 2 decimais")
    BigDecimal price,

    @NotNull(message = "Campo providerId não pode ser nulo")
    @Positive(message = "Campo providerId deve ser maior que zero")
    Long providerId,

    @NotNull(message = "Campo serviceId não pode ser nulo")
    @Positive(message = "Campo serviceId deve ser maior que zero")
    Long serviceId
) {}

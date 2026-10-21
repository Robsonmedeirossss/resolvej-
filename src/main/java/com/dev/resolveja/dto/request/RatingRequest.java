package com.dev.resolveja.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RatingRequest(
    @NotNull(message = "Campo note não pode ser nulo")
    @Min(value = 1, message = "Campo note deve ser no mínimo 1")
    @Max(value = 5, message = "Campo note deve ser no máximo 5")
    Short note,
    @Size(max = 500, message = "Campo comment deve ter no máximo 500 caracteres")
    String comment
) {}

package com.dev.resolveja.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ServiceRequest(
    @NotBlank(message = "Campo name não pode ser vazio")
    @Size(max = 250, message = "Campo name deve ter no máximo 250 caracteres")
    String name,
    @Size(max = 1000, message = "Campo description deve ter no máximo 1000 caracteres")
    String description,
    Boolean active
) {}

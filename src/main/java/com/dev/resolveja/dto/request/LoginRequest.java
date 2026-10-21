package com.dev.resolveja.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
    @Email(message = "Campo e-mail deve ter um valor válido") 
    @NotBlank(message = "Campo e-mail não pode ser vazio")
    String email,

    @NotBlank(message = "Campo password não pode ser vazio")
    @Size(min = 6, max = 250, message = "Campo password deve ter entre 6 e 250 caracteres")
    String password
) {}

package com.dev.resolveja.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record RegisterRequest(
    @NotBlank(message = "Campo name não pode ser vazio")
    @Size(max = 250, message = "Campo name deve ter no máximo 250 caracteres")
    String name,

    @Email(message = "Campo email deve ter um valor no formato válido") 
    @NotBlank(message = "Campo email não pode ser vazio")
    @Size(max = 250, message = "Campo email deve ter no máximo 250 caracteres")
    String email,

    @NotBlank(message = "Campo password não pode ser vazio")
    @Size(min = 6, max = 750, message = "Campo password deve ter entre 6 e 750 caracteres")
    String password,

    @Pattern(
        regexp = "CUSTOMER|PROVIDER",
        message = "Campo role deve ser CUSTOMER ou PROVIDER"
    )
    @NotNull(message = "Campo role é obrigatório")
    String role,

    @NotNull(message = "Campo birthDate não pode ser nulo")
    LocalDate birthDate
) {
   
}

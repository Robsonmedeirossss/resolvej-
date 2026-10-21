package com.dev.resolveja.dto.request.update;

import java.time.LocalDate;

import com.dev.resolveja.domain.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
    @Size(max = 250, message = "Campo name deve ter no máximo 250 caracteres")
    String name,
    @Email(message = "Campo email deve ter um valor no formato válido")
    @Size(max = 250, message = "Campo email deve ter no máximo 250 caracteres")
    String email,
    @Size(min = 6, max = 750, message = "Campo password deve ter entre 6 e 750 caracteres")
    String password,
    LocalDate birthDate
) {
    public User merge(User user) {
        if(this.name() != null && !this.name().isBlank()) {
            user.setName(this.name());
        }

        if(this.email() != null && !this.email().isBlank()) {
            user.setEmail(this.email());
        }

        if(this.password() != null && !this.password().isBlank()) {
            user.setPassword(this.password());
        }

        if(this.birthDate() != null) {
            user.setBirthDate(this.birthDate());
        }

        return user;
    }
}

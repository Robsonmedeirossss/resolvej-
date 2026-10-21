package com.dev.resolveja.dto.request.update;

import com.dev.resolveja.domain.Service;

import jakarta.validation.constraints.Size;

public record ServiceUpdateRequest(
    @Size(max = 250, message = "Campo name deve ter no máximo 250 caracteres")
    String name,
    @Size(max = 1000, message = "Campo description deve ter no máximo 1000 caracteres")
    String description,
    Boolean active
) {
    public Service merge(Service service) {
        if(this.name() != null && !this.name().isBlank()) {
            service.setName(this.name());
        }

        if(this.description() != null && !this.description().isBlank()) {
            service.setDescription(this.description());
        }

        if(this.active() != null) {
            service.setActive(this.active());
        }
 
        return service;
    }
}

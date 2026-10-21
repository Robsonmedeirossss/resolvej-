package com.dev.resolveja.dto.request.update;

import java.math.BigDecimal;

import com.dev.resolveja.domain.ProviderService;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;

public record ProviderServiceUpdateRequest(
    @Size(max = 1500, message = "Campo description deve ter no máximo 1500 caracteres")
    String description,
    Boolean active,
    @DecimalMin(value = "0.00", message = "Campo price não pode ser negativo")
    @Digits(integer = 5, fraction = 2, message = "Campo price deve ter no máximo 5 dígitos inteiros e 2 decimais")
    BigDecimal price
) {

    public ProviderService merge(ProviderService providerService) {
        
        if(this.description() != null && !this.description().isBlank()) {
            providerService.setDescription(this.description());
        }

        if(this.active() != null) {
            providerService.setActive(this.active());
        }

        if(this.price() != null) {
            providerService.setPrice(this.price());
        }
        
        return providerService;
    }
}

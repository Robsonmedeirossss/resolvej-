package com.dev.resolveja.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration 
public class SwaggerConfig {
    

    @Bean 
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(new Info()
            .title("ResolveJa")
            .version("1.0.0")
            .description(String.format("""
                Essa API tem como objetivo fornecer todos os endpoins
                necessários para as entidades de: usuário, serviço,
                serviços do prestador, solicitações de serviço e avaliações.
            """))
            .summary("API para sistema de prestadores de serviço")
        );
    }

}

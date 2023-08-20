package com.example.airbnb.Swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("Bearer Token", apiKeySecuritySchema()))
                .info(new Info().title("AirBNB").description("Written by: AirBNB"))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Token"));
    }

    private SecurityScheme apiKeySecuritySchema() {
        return new SecurityScheme()
                .name(HttpHeaders.AUTHORIZATION)
                .description("put your jwt token here!")
                .in(SecurityScheme.In.HEADER)
                .type(SecurityScheme.Type.HTTP)
                .scheme("Bearer");
    }
}
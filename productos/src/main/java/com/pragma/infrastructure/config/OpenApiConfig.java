package com.pragma.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "API de Productos",
        version = "1.0",
        description = "API REST para la gestión de productos",
        contact = @Contact(
            name = "Pragma",
            email = "contacto@pragma.com",
            url = "https://www.pragma.com"
        ),
        license = @License(
            name = "Apache 2.0",
            url = "http://www.apache.org/licenses/LICENSE-2.0.html"
        )
    ),
    servers = @Server(
        url = "/api/v1",
        description = "Servidor de Producción"
    )
)
public class OpenApiConfig {
} 
package com.pragma.ventas.infrastructure.config;

import com.pragma.ventas.application.exception.ProductNotFoundException;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class FeignConfig {
    
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Request.Options requestOptions() {
        return new Request.Options(
            5000, // connectTimeout
            5000, // readTimeout
            true  // followRedirects
        );
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(
            100,    // initial interval
            1000,   // max interval
            3       // max attempts
        );
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            if (response.status() == HttpStatus.NOT_FOUND.value()) {
                return new ProductNotFoundException("Producto no encontrado");
            }
            return new RuntimeException("Error en la comunicación con el servicio de productos");
        };
    }
} 
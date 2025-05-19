package com.pragma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Clase principal de la aplicación de Ventas.
 * Habilita los clientes Feign para la comunicación con otros microservicios.
 */
@SpringBootApplication
@EnableFeignClients
public class VentasApplication {

    public static void main(String[] args) {
        SpringApplication.run(VentasApplication.class, args);
    }

}

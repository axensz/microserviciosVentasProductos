package com.pragma.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal para la aplicación de microservicio de Productos.
 * Esta clase es el punto de entrada para la aplicación Spring Boot.
 * <p>
 * La anotación {@code @SpringBootApplication} habilita la autoconfiguración de Spring Boot,
 * el escaneo de componentes y la configuración específica de Spring Boot.
 * </p>
 *
 */
@SpringBootApplication
public class ProductosApplication {

    /**
     * Método principal que sirve como punto de entrada para la aplicación Spring Boot.
     *
     * @param args Argumentos de línea de comandos pasados al iniciar la aplicación.
     */
    public static void main(String[] args) {
        SpringApplication.run(ProductosApplication.class, args);
    }
}
package com.pragma.ventas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Clase principal para la aplicación de microservicio de Ventas.
 * Esta clase es el punto de entrada para la aplicación Spring Boot.
 * <p>
 * La anotación {@code @SpringBootApplication} habilita la autoconfiguración de Spring Boot,
 * el escaneo de componentes y la configuración específica de Spring Boot.
 * </p>
 * <p>
 * La anotación {@code @EnableFeignClients} habilita el cliente Feign para la comunicación
 * con otros microservicios, en este caso, con el microservicio de Productos.
 * </p>
 *
 * @version 1.0
 * @since 2025-05-20
 */
@SpringBootApplication
@EnableFeignClients
public class VentasApplication {

	/**
	 * Método principal que sirve como punto de entrada para la aplicación Spring Boot.
	 *
	 * @param args Argumentos de línea de comandos pasados al iniciar la aplicación.
	 */
	public static void main(String[] args) {
		SpringApplication.run(VentasApplication.class, args);
	}

}

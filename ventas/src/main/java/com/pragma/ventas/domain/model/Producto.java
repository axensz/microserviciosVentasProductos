package com.pragma.ventas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo de dominio que representa un producto en el sistema.
 * Este modelo contiene la información básica de un producto que es necesaria
 * para el proceso de ventas.
 *
 * @version 1.0
 * @since 2025-05-20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    /**
     * Identificador único del producto.
     */
    private Long id;

    /**
     * Nombre del producto.
     */
    private String nombre;

    /**
     * Descripción detallada del producto.
     */
    private String descripcion;

    /**
     * Precio unitario del producto.
     */
    private Double precio;

    /**
     * Cantidad disponible en inventario.
     */
    private Integer stock;
} 
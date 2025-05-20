package com.pragma.ventas.domain.repository;

import com.pragma.ventas.domain.model.Producto;

/**
 * Interfaz que define las operaciones para interactuar con el servicio de productos.
 * Este gateway actúa como puerto de salida en la arquitectura hexagonal,
 * permitiendo la comunicación con el microservicio de productos.
 *
 * @version 1.0
 * @since 2025-05-20
 */
public interface IProductoGateway {
    
    /**
     * Valida si un producto existe en el sistema.
     *
     * @param productoId ID del producto a validar
     * @return true si el producto existe, false en caso contrario
     */
    boolean validarExistenciaProducto(Long productoId);

    /**
     * Obtiene la información de un producto específico.
     *
     * @param productoId ID del producto a obtener
     * @return Objeto Producto con la información del producto
     * @throws RuntimeException si el producto no existe o hay un error en la comunicación
     */
    Producto obtenerProducto(Long productoId);
} 
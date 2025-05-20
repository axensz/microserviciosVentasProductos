package com.pragma.ventas.domain.repository;

import com.pragma.ventas.domain.model.Venta;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define las operaciones de persistencia para las ventas.
 * Este gateway actúa como puerto de salida en la arquitectura hexagonal,
 * permitiendo la persistencia de ventas en diferentes tipos de almacenamiento.
 *
 * @version 1.0
 * @since 2025-05-20
 */
public interface VentaGateway {
    
    /**
     * Guarda una venta en el sistema de persistencia.
     *
     * @param venta La venta a guardar
     * @return La venta guardada con su ID asignado
     */
    Venta guardarVenta(Venta venta);

    /**
     * Obtiene todas las ventas almacenadas.
     *
     * @return Lista de todas las ventas
     */
    List<Venta> obtenerVentas();

    /**
     * Busca una venta por su ID.
     *
     * @param id ID de la venta a buscar
     * @return Optional que contiene la venta si existe, o vacío si no se encuentra
     */
    Optional<Venta> obtenerVentaPorId(Long id);

    /**
     * Obtiene todas las ventas que incluyen un producto específico.
     *
     * @param productoId ID del producto a buscar en las ventas
     * @return Lista de ventas que incluyen el producto especificado
     */
    List<Venta> obtenerVentasPorProducto(Long productoId);

    /**
     * Obtiene todas las ventas realizadas en una fecha específica.
     *
     * @param fecha Fecha para filtrar las ventas
     * @return Lista de ventas realizadas en la fecha especificada
     */
    List<Venta> obtenerVentasPorFecha(LocalDate fecha);
} 
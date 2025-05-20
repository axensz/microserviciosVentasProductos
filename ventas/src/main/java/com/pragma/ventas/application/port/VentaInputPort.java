package com.pragma.ventas.application.port;

import com.pragma.ventas.domain.model.Venta;
import com.pragma.ventas.infrastructure.input.dto.VentaRequestDto;

import java.time.LocalDate;
import java.util.List;

/**
 * Puerto de entrada para la gestión de ventas.
 * Define los casos de uso que la aplicación ofrece para interactuar con las ventas.
 * Las implementaciones de esta interfaz orquestarán la lógica de negocio
 * y la interacción con los puertos de salida (repositorios).
 *
 * @version 1.0
 * @since 2025-05-20
 */
public interface VentaInputPort {
    /**
     * Registra una nueva venta en el sistema.
     *
     * @param ventaDto El objeto {@link VentaRequestDto} con los datos de la venta.
     * @return La venta registrada con su ID asignado.
     * @throws IllegalArgumentException si los datos de la venta son inválidos.
     * @throws ProductNotFoundException si algún producto no existe.
     */
    Venta registrarVenta(VentaRequestDto ventaDto);
    
    /**
     * Obtiene todas las ventas registradas en el sistema.
     *
     * @return Lista de ventas registradas.
     */
    List<Venta> obtenerVentas();
    
    /**
     * Obtiene una venta específica por su ID.
     *
     * @param id El ID de la venta a buscar.
     * @return La venta encontrada.
     * @throws IllegalArgumentException si la venta no existe.
     */
    Venta obtenerVentaPorId(Long id);
    
    /**
     * Obtiene todas las ventas que incluyen un producto específico.
     *
     * @param productoId El ID del producto a buscar en las ventas.
     * @return Lista de ventas que contienen el producto.
     */
    List<Venta> obtenerVentasPorProducto(Long productoId);
    
    /**
     * Obtiene todas las ventas realizadas en una fecha específica.
     *
     * @param fecha La fecha para filtrar las ventas.
     * @return Lista de ventas realizadas en la fecha especificada.
     */
    List<Venta> obtenerVentasPorFecha(LocalDate fecha);
} 
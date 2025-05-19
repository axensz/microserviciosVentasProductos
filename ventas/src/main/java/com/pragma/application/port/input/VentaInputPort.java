package com.pragma.application.port.input;

import com.pragma.domain.model.Venta;
import java.util.List;

/**
 * Puerto de entrada para la gestión de ventas.
 * Define los casos de uso que la aplicación ofrece para interactuar con las ventas.
 * Las implementaciones de esta interfaz orquestarán la lógica de negocio
 * y la interacción con los puertos de salida (repositorios).
 */
public interface VentaInputPort {

    /**
     * Registra una nueva venta en el sistema.
     * Este método se encarga de validar el stock disponible de los productos,
     * actualizar el stock en el microservicio de Productos, y persistir la venta.
     *
     * @param venta El objeto {@link Venta} a registrar. No debe ser nulo.
     * @return La venta registrada, usualmente con su ID asignado por el sistema de persistencia.
     * @throws com.pragma.application.exception.ValidationException Si los datos de la venta no son válidos.
     * @throws com.pragma.application.exception.StockInsuficienteException Si no hay suficiente stock para alguno de los productos.
     */
    Venta registrarVenta(Venta venta);

    /**
     * Obtiene una venta específica por su identificador único.
     *
     * @param id El ID de la venta a obtener.
     * @return La {@link Venta} encontrada.
     * @throws com.pragma.application.exception.VentaNotFoundException Si no se encuentra la venta con el ID especificado.
     */
    Venta obtenerVentaPorId(Long id);

    /**
     * Obtiene una lista de todas las ventas registradas en el sistema.
     * Este método se utiliza para generar el reporte de ventas.
     *
     * @return Una lista de {@link Venta}. La lista puede estar vacía si no hay ventas.
     */
    List<Venta> obtenerTodasLasVentas();
}
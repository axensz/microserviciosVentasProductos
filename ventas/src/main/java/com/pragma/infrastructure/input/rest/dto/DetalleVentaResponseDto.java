package com.pragma.infrastructure.input.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) para las respuestas de detalles de venta.
 * Contiene los datos del detalle de venta que se envían al cliente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaResponseDto {

    /**
     * Identificador único del detalle de venta.
     */
    private Long id;

    /**
     * Identificador del producto vendido.
     */
    private Long productoId;

    /**
     * Nombre del producto vendido.
     */
    private String nombreProducto;

    /**
     * Cantidad del producto vendido.
     */
    private Integer cantidad;

    /**
     * Precio unitario del producto al momento de la venta.
     */
    private Double precioUnitario;

    /**
     * Subtotal del detalle (cantidad * precioUnitario).
     */
    private Double subtotal;

    /**
     * Stock restante del producto después de la venta.
     */
    private Integer stockRestante;
}
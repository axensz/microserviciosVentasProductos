package com.pragma.infrastructure.input.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO (Data Transfer Object) para las respuestas de ventas.
 * Contiene los datos de la venta que se envían al cliente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaResponseDto {

    /**
     * Identificador único de la venta.
     */
    private Long id;

    /**
     * Fecha y hora en que se realizó la venta.
     */
    private LocalDateTime fecha;

    /**
     * Lista de detalles de la venta.
     */
    private List<DetalleVentaResponseDto> detalles;

    /**
     * Total de la venta.
     */
    private Double total;
}
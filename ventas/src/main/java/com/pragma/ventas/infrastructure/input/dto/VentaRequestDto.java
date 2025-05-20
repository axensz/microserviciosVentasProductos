package com.pragma.ventas.infrastructure.input.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * DTO para la solicitud de registro de ventas.
 * Este DTO contiene la información necesaria para registrar una nueva venta,
 * incluyendo los detalles de los productos vendidos.
 *
 * @version 1.0
 * @since 2025-05-20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VentaRequestDto {
    private String clienteId;
    private List<DetalleVentaDto> detalles;

    /**
     * DTO para los detalles de una venta en la solicitud.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetalleVentaDto {
        private Long productoId;
        private Integer cantidad;
    }
} 
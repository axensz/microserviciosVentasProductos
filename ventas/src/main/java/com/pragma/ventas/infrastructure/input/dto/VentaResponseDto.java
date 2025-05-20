package com.pragma.ventas.infrastructure.input.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO para la respuesta de ventas.
 * Este DTO contiene la información de una venta que será enviada al cliente,
 * incluyendo sus detalles.
 *
 * @version 1.0
 * @since 2025-05-20
 */
@Data
@Builder
public class VentaResponseDto {
    private Long id;
    private String clienteId;
    private LocalDateTime fecha;
    private Double total;
    private List<DetalleVentaDto> detalles;

    /**
     * DTO para los detalles de una venta en la respuesta.
     */
    @Data
    @Builder
    public static class DetalleVentaDto {
        private Long productoId;
        private Integer cantidad;
        private Double precioUnitario;
        private Double subtotal;
    }
} 
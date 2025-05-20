package com.pragma.ventas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Venta {
    private Long id;
    private String clienteId;
    private LocalDateTime fecha;
    private Double total;
    private List<DetalleVenta> detalles;
} 
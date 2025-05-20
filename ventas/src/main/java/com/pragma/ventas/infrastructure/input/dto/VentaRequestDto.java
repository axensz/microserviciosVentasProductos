package com.pragma.ventas.infrastructure.input.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class VentaRequestDto {
    @NotBlank(message = "El ID del cliente es requerido")
    private String clienteId;
    
    @NotEmpty(message = "La venta debe tener al menos un detalle")
    @Valid
    private List<DetalleVentaRequestDto> detalles;
} 
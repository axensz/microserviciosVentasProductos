package com.pragma.ventas.infrastructure.input.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DetalleVentaRequestDto {
    @NotNull(message = "El ID del producto es requerido")
    private Long productoId;
    
    @NotNull(message = "La cantidad es requerida")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;
} 
package com.pragma.infrastructure.input.rest.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO (Data Transfer Object) para las solicitudes de creación de ventas.
 * Contiene los datos que se esperan del cliente para una venta.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaRequestDto {

    /**
     * Lista de detalles de la venta.
     * No puede estar vacía y cada detalle debe ser válido.
     */
    @NotEmpty(message = "La venta debe tener al menos un detalle.")
    @Valid
    private List<DetalleVentaRequestDto> detalles;
}
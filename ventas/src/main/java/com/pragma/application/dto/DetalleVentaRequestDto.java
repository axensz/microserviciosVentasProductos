package com.pragma.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) para las solicitudes de creación de detalles de venta.
 * Contiene los datos que se esperan del cliente para un detalle de venta.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaRequestDto {

    /**
     * Identificador del producto a vender.
     * No puede ser nulo.
     */
    @NotNull(message = "El ID del producto no puede ser nulo.")
    private Long productoId;

    /**
     * Cantidad del producto a vender.
     * No puede ser nula y debe ser un valor positivo.
     */
    @NotNull(message = "La cantidad no puede ser nula.")
    @Positive(message = "La cantidad debe ser un valor positivo.")
    private Integer cantidad;
}
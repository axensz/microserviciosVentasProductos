package com.pragma.infrastructure.input.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

/**
 * DTO (Data Transfer Object) para las solicitudes de creación y actualización de productos.
 * Contiene los datos que se esperan del cliente.
 * Incluye validaciones de Jakarta Bean Validation.
 */
public class ProductRequestDto {

    /**
     * Nombre del producto.
     * No puede estar en blanco y debe tener entre 3 y 255 caracteres.
     */
    @NotBlank(message = "El nombre del producto no puede estar en blanco.")
    @Size(min = 3, max = 255, message = "El nombre del producto debe tener entre 3 y 255 caracteres.")
    private String nombre;

    /**
     * Descripción del producto.
     * Puede ser nulo, pero si se proporciona, debe tener como máximo 1000 caracteres.
     */
    @Size(max = 1000, message = "La descripción del producto no puede exceder los 1000 caracteres.")
    private String descripcion;

    /**
     * Precio del producto.
     * No puede ser nulo y debe ser un valor positivo.
     */
    @NotNull(message = "El precio del producto no puede ser nulo.")
    @Positive(message = "El precio del producto debe ser un valor positivo.")
    private Double precio;

    /**
     * Stock del producto.
     * No puede ser nulo y debe ser un valor positivo o cero.
     */
    @NotNull(message = "El stock del producto no puede ser nulo.")
    @PositiveOrZero(message = "El stock del producto debe ser un valor positivo o cero.")
    private Integer stock;

    // Getters y Setters

    /**
     * Obtiene el nombre del producto.
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     * @param nombre El nuevo nombre para el producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del producto.
     * @return La descripción del producto.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del producto.
     * @param descripcion La nueva descripción para el producto.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el precio del producto.
     * @return El precio del producto.
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     * @param precio El nuevo precio para el producto.
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el stock del producto.
     * @return El stock del producto.
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * Establece el stock del producto.
     * @param stock El nuevo stock para el producto.
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }
}

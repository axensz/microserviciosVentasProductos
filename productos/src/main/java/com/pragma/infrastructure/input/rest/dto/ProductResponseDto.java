package com.pragma.infrastructure.input.rest.dto;

/**
 * DTO (Data Transfer Object) para las respuestas de las operaciones de productos.
 * Contiene los datos del producto que se envían al cliente.
 */
public class ProductResponseDto {

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;

    // Getters y Setters

    /**
     * Obtiene el ID del producto.
     * @return El ID del producto.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del producto.
     * @param id El ID del producto.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     * @param nombre El nombre del producto.
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
     * @param descripcion La descripción del producto.
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
     * @param precio El precio del producto.
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
     * @param stock El stock del producto.
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }
}

package com.pragma.infrastructure.output.feign.dto;

/**
 * DTO (Data Transfer Object) para las respuestas del microservicio de Productos.
 * Contiene los datos del producto que se reciben del microservicio de Productos.
 * Esta clase debe coincidir con el formato de respuesta del microservicio de Productos.
 */
public class ProductoResponseDto {

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;

    /**
     * Constructor por defecto.
     * Requerido por algunos frameworks de serialización/deserialización.
     */
    public ProductoResponseDto() {
    }

    /**
     * Constructor completo para crear una instancia de ProductoResponseDto.
     *
     * @param id El identificador único del producto.
     * @param nombre El nombre del producto.
     * @param descripcion La descripción del producto.
     * @param precio El precio del producto.
     * @param stock El stock del producto.
     */
    public ProductoResponseDto(Long id, String nombre, String descripcion, Double precio, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    /**
     * Obtiene el identificador único del producto.
     *
     * @return El id del producto.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del producto.
     *
     * @param id El nuevo id para el producto.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param nombre El nuevo nombre para el producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del producto.
     *
     * @return La descripción del producto.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del producto.
     *
     * @param descripcion La nueva descripción para el producto.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return El precio del producto.
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     *
     * @param precio El nuevo precio para el producto.
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el stock del producto.
     *
     * @return El stock del producto.
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * Establece el stock del producto.
     *
     * @param stock El nuevo stock para el producto.
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
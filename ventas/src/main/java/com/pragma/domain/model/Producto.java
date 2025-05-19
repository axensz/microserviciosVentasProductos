package com.pragma.domain.model;

/**
 * Representa un producto en el dominio de la aplicación de ventas.
 * Esta clase es una representación simplificada del producto del microservicio de Productos,
 * conteniendo solo la información necesaria para el microservicio de Ventas.
 */
public class Producto {

    /**
     * Identificador único del producto.
     */
    private Long id;

    /**
     * Nombre del producto.
     */
    private String nombre;

    /**
     * Descripción detallada del producto.
     */
    private String descripcion;

    /**
     * Precio unitario del producto.
     */
    private Double precio;

    /**
     * Cantidad de unidades del producto disponibles en stock.
     */
    private Integer stock;

    /**
     * Constructor por defecto.
     * Requerido por algunos frameworks de persistencia.
     */
    public Producto() {
    }

    /**
     * Constructor completo para crear una instancia de Producto.
     *
     * @param id El identificador único del producto.
     * @param nombre El nombre del producto.
     * @param descripcion La descripción detallada del producto.
     * @param precio El precio unitario del producto.
     * @param stock La cantidad de unidades en stock.
     */
    public Producto(Long id, String nombre, String descripcion, Double precio, Integer stock) {
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
     * Obtiene la descripción detallada del producto.
     *
     * @return La descripción del producto.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción detallada del producto.
     *
     * @param descripcion La nueva descripción para el producto.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el precio unitario del producto.
     *
     * @return El precio del producto.
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio unitario del producto.
     *
     * @param precio El nuevo precio para el producto.
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la cantidad de unidades del producto disponibles en stock.
     *
     * @return El stock del producto.
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * Establece la cantidad de unidades del producto disponibles en stock.
     *
     * @param stock El nuevo stock para el producto.
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
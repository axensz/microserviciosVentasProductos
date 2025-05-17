package com.pragma.infrastructure.output.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad JPA que representa un producto en la base de datos.
 * Esta clase se mapea a la tabla "productos" y es utilizada por Spring Data JPA
 * para las operaciones de persistencia.
 */
@Entity
@Table(name = "productos") // Define el nombre de la tabla en la base de datos
public class ProductoEntity {

    /**
     * Identificador único del producto, generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estrategia de generación de ID (autoincremental)
    private Long id;

    /**
     * Nombre del producto.
     * No puede ser nulo y tiene una longitud máxima de 255 caracteres.
     */
    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;

    /**
     * Descripción detallada del producto.
     * Puede ser nulo y tiene una longitud máxima de 1000 caracteres.
     */
    @Column(name = "descripcion", length = 1000)
    private String descripcion;

    /**
     * Precio unitario del producto.
     * No puede ser nulo.
     */
    @Column(name = "precio", nullable = false)
    private Double precio;

    /**
     * Cantidad de unidades del producto disponibles en stock.
     * No puede ser nulo.
     */
    @Column(name = "stock", nullable = false)
    private Integer stock;

    /**
     * Constructor por defecto.
     * Requerido por JPA.
     */
    public ProductoEntity() {
    }

    /**
     * Constructor completo para crear una instancia de ProductoEntity.
     *
     * @param id El identificador único del producto.
     * @param nombre El nombre del producto.
     * @param descripcion La descripción detallada del producto.
     * @param precio El precio unitario del producto.
     * @param stock La cantidad de unidades en stock.
     */
    public ProductoEntity(Long id, String nombre, String descripcion, Double precio, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters y Setters

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

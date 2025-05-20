package com.pragma.ventas.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Representa un detalle de venta en el dominio de la aplicación.
 * Contiene información sobre un producto específico vendido, incluyendo
 * su cantidad, precio unitario y subtotal.
 *
 * @version 1.0
 * @since 2025-05-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detalles_venta")
public class DetalleVenta {
    /**
     * Identificador único del detalle de venta.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Identificador del producto vendido.
     * No puede ser nulo.
     */
    @Column(name = "producto_id", nullable = false)
    private Long productoId;
    
    /**
     * Cantidad de unidades del producto vendidas.
     * No puede ser nulo.
     */
    @Column(nullable = false)
    private Integer cantidad;
    
    /**
     * Precio unitario del producto al momento de la venta.
     * No puede ser nulo.
     */
    @Column(nullable = false)
    private Double precioUnitario;
    
    /**
     * Subtotal del detalle (precio unitario * cantidad).
     * No puede ser nulo.
     */
    @Column(nullable = false)
    private Double subtotal;
    
    /**
     * Referencia a la venta a la que pertenece este detalle.
     * No puede ser nulo.
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id", nullable = false)
    private Venta venta;

    /**
     * Constructor con builder pattern.
     *
     * @param id Identificador único del detalle
     * @param productoId Identificador del producto
     * @param cantidad Cantidad de unidades vendidas
     * @param precioUnitario Precio unitario del producto
     * @param subtotal Subtotal del detalle
     */
    @Builder
    public DetalleVenta(Long id, Long productoId, Integer cantidad, Double precioUnitario, Double subtotal) {
        this.id = id;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }
} 
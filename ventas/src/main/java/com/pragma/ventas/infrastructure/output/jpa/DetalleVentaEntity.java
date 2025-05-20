package com.pragma.ventas.infrastructure.output.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad JPA que representa un detalle de venta en la base de datos.
 * Esta clase mapea la tabla de detalles de venta y su relación con la venta.
 *
 * @version 1.0
 * @since 2025-05-20
 */
@Entity
@Table(name = "detalles_venta")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaEntity {
    
    /**
     * Identificador único del detalle de venta.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Identificador del producto vendido.
     */
    @Column(nullable = false)
    private Long productoId;

    /**
     * Cantidad de unidades vendidas.
     */
    @Column(nullable = false)
    private Integer cantidad;

    /**
     * Precio unitario del producto al momento de la venta.
     */
    @Column(nullable = false)
    private Double precioUnitario;

    /**
     * Subtotal del detalle (precio unitario * cantidad).
     */
    @Column(nullable = false)
    private Double subtotal;

    /**
     * Referencia a la venta a la que pertenece este detalle.
     * Relación muchos a uno con la entidad VentaEntity.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id", nullable = false)
    private VentaEntity venta;
} 
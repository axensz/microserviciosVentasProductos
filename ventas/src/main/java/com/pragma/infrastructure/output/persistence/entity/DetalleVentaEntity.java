package com.pragma.infrastructure.output.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad JPA que representa un detalle de venta en la base de datos.
 */
@Entity
@Table(name = "detalles_venta")
@Data
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
     * Venta a la que pertenece este detalle.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id", nullable = false)
    private VentaEntity venta;

    public void setVenta(VentaEntity venta) {
        this.venta = venta;
    }

    /**
     * Identificador del producto vendido.
     */
    @Column(name = "producto_id", nullable = false)
    private Long productoId;

    /**
     * Nombre del producto vendido.
     */
    @Column(name = "nombre_producto", nullable = false)
    private String nombreProducto;

    /**
     * Cantidad del producto vendido.
     */
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    /**
     * Precio unitario del producto al momento de la venta.
     */
    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    /**
     * Subtotal del detalle (cantidad * precioUnitario).
     */
    @Column(name = "subtotal", nullable = false)
    private Double subtotal;

    /**
     * Stock restante del producto después de la venta.
     */
    @Column(name = "stock_restante", nullable = false)
    private Integer stockRestante;
}
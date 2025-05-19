package com.pragma.infrastructure.output.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad JPA que representa una venta en la base de datos.
 */
@Entity
@Table(name = "ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaEntity {

    /**
     * Identificador único de la venta.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Fecha y hora en que se realizó la venta.
     */
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    /**
     * Total de la venta.
     */
    @Column(name = "total", nullable = false)
    private Double total;

    /**
     * Lista de detalles de la venta.
     */
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVentaEntity> detalles = new ArrayList<>();

    /**
     * Método helper para agregar un detalle a la venta y establecer la relación bidireccional.
     *
     * @param detalle El detalle a agregar.
     */
    public void addDetalle(DetalleVentaEntity detalle) {
        detalles.add(detalle);
        detalle.setVenta(this);
    }

    /**
     * Método helper para remover un detalle de la venta y romper la relación bidireccional.
     *
     * @param detalle El detalle a remover.
     */
    public void removeDetalle(DetalleVentaEntity detalle) {
        detalles.remove(detalle);
        detalle.setVenta(null);
    }
}
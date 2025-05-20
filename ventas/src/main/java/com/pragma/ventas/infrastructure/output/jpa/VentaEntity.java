package com.pragma.ventas.infrastructure.output.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad JPA que representa una venta en la base de datos.
 * Esta clase mapea la tabla de ventas y sus relaciones con los detalles de venta.
 *
 * @version 1.0
 * @since 2025-05-20
 */
@Entity
@Table(name = "ventas")
@Data
@Builder
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
     * Identificador del cliente que realizó la venta.
     */
    @Column(nullable = false)
    private String clienteId;

    /**
     * Fecha y hora en que se realizó la venta.
     */
    @Column(nullable = false)
    private LocalDateTime fecha;

    /**
     * Monto total de la venta.
     */
    @Column(nullable = false)
    private Double total;

    /**
     * Lista de detalles de la venta.
     * Relación uno a muchos con la entidad DetalleVentaEntity.
     */
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<DetalleVentaEntity> detalles = new ArrayList<>();
} 
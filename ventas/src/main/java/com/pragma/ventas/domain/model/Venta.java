package com.pragma.ventas.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una venta en el dominio de la aplicación.
 * Contiene información detallada sobre la venta, incluyendo el cliente,
 * fecha, total y los detalles de los productos vendidos.
 *
 * @version 1.0
 * @since 2025-05-20
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "ventas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Venta {
    /**
     * Identificador único de la venta.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Identificador del cliente que realiza la compra.
     * No puede ser nulo.
     */
    @Column(nullable = false)
    private String clienteId;
    
    /**
     * Fecha y hora en que se realizó la venta.
     * No puede ser nulo.
     */
    @Column(nullable = false)
    private LocalDateTime fecha;
    
    /**
     * Monto total de la venta.
     * No puede ser nulo.
     */
    @Column(nullable = false)
    private Double total;
    
    /**
     * Lista de detalles de la venta.
     * Cada detalle representa un producto vendido con su cantidad y precio.
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DetalleVenta> detalles = new ArrayList<>();

    /**
     * Constructor con builder pattern.
     *
     * @param id Identificador único de la venta
     * @param clienteId Identificador del cliente
     * @param fecha Fecha y hora de la venta
     * @param total Monto total de la venta
     * @param detalles Lista de detalles de la venta
     */
    @Builder
    public Venta(Long id, String clienteId, LocalDateTime fecha, Double total, List<DetalleVenta> detalles) {
        this.id = id;
        this.clienteId = clienteId;
        this.fecha = fecha;
        this.total = total;
        this.detalles = detalles != null ? detalles : new ArrayList<>();
    }

    /**
     * Agrega un detalle a la venta y actualiza el total.
     *
     * @param detalle El detalle de venta a agregar
     * @throws IllegalArgumentException si el detalle es nulo
     */
    public void agregarDetalle(DetalleVenta detalle) {
        if (detalle == null) {
            throw new IllegalArgumentException("El detalle no puede ser nulo");
        }
        detalles.add(detalle);
        detalle.setVenta(this);
        actualizarTotal();
    }

    /**
     * Remueve un detalle de la venta y actualiza el total.
     *
     * @param detalle El detalle de venta a remover
     * @throws IllegalArgumentException si el detalle es nulo
     */
    public void removerDetalle(DetalleVenta detalle) {
        if (detalle == null) {
            throw new IllegalArgumentException("El detalle no puede ser nulo");
        }
        if (detalles.remove(detalle)) {
            detalle.setVenta(null);
            actualizarTotal();
        }
    }

    /**
     * Actualiza el total de la venta sumando los subtotales de todos los detalles.
     */
    private void actualizarTotal() {
        this.total = detalles.stream()
                .mapToDouble(DetalleVenta::getSubtotal)
                .sum();
    }

    public boolean tieneDetalle(Long productoId) {
        return detalles.stream()
            .anyMatch(detalle -> detalle.getProductoId() != null && detalle.getProductoId().equals(productoId));
    }

    public DetalleVenta obtenerDetalle(Long productoId) {
        return detalles.stream()
            .filter(detalle -> detalle.getProductoId() != null && detalle.getProductoId().equals(productoId))
            .findFirst()
            .orElse(null);
    }
} 
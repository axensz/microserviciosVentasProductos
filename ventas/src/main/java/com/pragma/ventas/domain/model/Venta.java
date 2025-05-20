package com.pragma.ventas.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ventas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "cliente_id", nullable = false)
    private String clienteId;
    
    @Column(nullable = false)
    private LocalDateTime fecha;
    
    @Column(nullable = false)
    private Double total;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DetalleVenta> detalles = new ArrayList<>();

    @Builder
    public Venta(Long id, String clienteId, LocalDateTime fecha, Double total) {
        this.id = id;
        this.clienteId = clienteId;
        this.fecha = fecha;
        this.total = total;
        this.detalles = new ArrayList<>();
    }

    public void agregarDetalle(DetalleVenta detalle) {
        if (detalle == null) {
            throw new IllegalArgumentException("El detalle no puede ser nulo");
        }
        detalles.add(detalle);
        detalle.setVenta(this);
        actualizarTotal();
    }

    public void removerDetalle(DetalleVenta detalle) {
        if (detalle == null) {
            throw new IllegalArgumentException("El detalle no puede ser nulo");
        }
        if (detalles.remove(detalle)) {
            detalle.setVenta(null);
            actualizarTotal();
        }
    }

    private void actualizarTotal() {
        this.total = detalles.stream()
            .mapToDouble(DetalleVenta::getSubtotal)
            .sum();
    }

    public boolean tieneDetalle(Long productoId) {
        return detalles.stream()
            .anyMatch(detalle -> detalle.getProductoId().equals(productoId));
    }

    public DetalleVenta obtenerDetalle(Long productoId) {
        return detalles.stream()
            .filter(detalle -> detalle.getProductoId().equals(productoId))
            .findFirst()
            .orElse(null);
    }
} 
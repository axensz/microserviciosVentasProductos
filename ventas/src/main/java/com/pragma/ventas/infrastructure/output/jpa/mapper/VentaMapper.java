package com.pragma.ventas.infrastructure.output.jpa.mapper;

import com.pragma.ventas.domain.model.DetalleVenta;
import com.pragma.ventas.domain.model.Venta;
import com.pragma.ventas.infrastructure.output.jpa.DetalleVentaEntity;
import com.pragma.ventas.infrastructure.output.jpa.VentaEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Mapper que convierte entre entidades JPA y modelos de dominio para las ventas.
 * Esta clase se encarga de la transformación bidireccional entre las capas de
 * infraestructura y dominio.
 *
 * @version 1.0
 * @since 2025-05-20
 */
@Component
public class VentaMapper {

    /**
     * Convierte una entidad JPA a un modelo de dominio.
     *
     * @param entity Entidad JPA a convertir
     * @return Modelo de dominio Venta
     */
    public Venta toDomain(VentaEntity entity) {
        if (entity == null) {
            return null;
        }

        return Venta.builder()
            .id(entity.getId())
            .clienteId(entity.getClienteId())
            .fecha(entity.getFecha())
            .total(entity.getTotal())
            .detalles(entity.getDetalles().stream()
                .map(this::toDomain)
                .collect(Collectors.toList()))
            .build();
    }

    /**
     * Convierte un modelo de dominio a una entidad JPA.
     *
     * @param domain Modelo de dominio a convertir
     * @return Entidad JPA VentaEntity
     */
    public VentaEntity toEntity(Venta domain) {
        if (domain == null) {
            return null;
        }

        var entity = VentaEntity.builder()
            .id(domain.getId())
            .clienteId(domain.getClienteId())
            .fecha(domain.getFecha())
            .total(domain.getTotal())
            .build();

        var detalles = domain.getDetalles().stream()
            .map(detalle -> toEntity(detalle, entity))
            .collect(Collectors.toList());

        entity.setDetalles(detalles);
        return entity;
    }

    /**
     * Convierte una entidad JPA de detalle de venta a un modelo de dominio.
     *
     * @param entity Entidad JPA a convertir
     * @return Modelo de dominio DetalleVenta
     */
    private DetalleVenta toDomain(DetalleVentaEntity entity) {
        if (entity == null) {
            return null;
        }

        return DetalleVenta.builder()
            .id(entity.getId())
            .productoId(entity.getProductoId())
            .cantidad(entity.getCantidad())
            .precioUnitario(entity.getPrecioUnitario())
            .subtotal(entity.getSubtotal())
            .build();
    }

    /**
     * Convierte un modelo de dominio de detalle de venta a una entidad JPA.
     *
     * @param domain Modelo de dominio a convertir
     * @param ventaEntity Entidad JPA de la venta a la que pertenece el detalle
     * @return Entidad JPA DetalleVentaEntity
     */
    private DetalleVentaEntity toEntity(DetalleVenta domain, VentaEntity ventaEntity) {
        if (domain == null) {
            return null;
        }

        return DetalleVentaEntity.builder()
            .id(domain.getId())
            .productoId(domain.getProductoId())
            .cantidad(domain.getCantidad())
            .precioUnitario(domain.getPrecioUnitario())
            .subtotal(domain.getSubtotal())
            .venta(ventaEntity)
            .build();
    }
} 
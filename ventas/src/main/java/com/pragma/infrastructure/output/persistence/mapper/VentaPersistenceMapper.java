package com.pragma.infrastructure.output.persistence.mapper;

import com.pragma.domain.model.DetalleVenta;
import com.pragma.domain.model.Venta;
import com.pragma.infrastructure.output.persistence.entity.DetalleVentaEntity;
import com.pragma.infrastructure.output.persistence.entity.VentaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Interfaz para el mapeo entre entidades de persistencia y entidades de dominio.
 * Utiliza MapStruct para generar la implementación automáticamente.
 */
@Mapper(componentModel = "spring")
public interface VentaPersistenceMapper {

    /**
     * Convierte una entidad de venta a un modelo de dominio.
     *
     * @param ventaEntity La entidad de venta.
     * @return El modelo de dominio Venta.
     */
    Venta toVenta(VentaEntity ventaEntity);

    /**
     * Convierte un modelo de dominio de venta a una entidad de persistencia.
     *
     * @param venta El modelo de dominio Venta.
     * @return La entidad de venta.
     */
    VentaEntity toVentaEntity(Venta venta);

    /**
     * Convierte una entidad de detalle de venta a un modelo de dominio.
     *
     * @param detalleVentaEntity La entidad de detalle de venta.
     * @return El modelo de dominio DetalleVenta.
     */
    @Mapping(target = "productoId", source = "productoId")
    DetalleVenta toDetalleVenta(DetalleVentaEntity detalleVentaEntity);

    /**
     * Convierte un modelo de dominio de detalle de venta a una entidad de persistencia.
     *
     * @param detalleVenta El modelo de dominio DetalleVenta.
     * @return La entidad de detalle de venta.
     */
    @Mapping(target = "venta", ignore = true)
    DetalleVentaEntity toDetalleVentaEntity(DetalleVenta detalleVenta);

    /**
     * Convierte una lista de entidades de venta a una lista de modelos de dominio.
     *
     * @param ventaEntities La lista de entidades de venta.
     * @return La lista de modelos de dominio Venta.
     */
    List<Venta> toVentaList(List<VentaEntity> ventaEntities);
}
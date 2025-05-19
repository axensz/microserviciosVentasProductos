package com.pragma.infrastructure.input.rest.mapper;

import com.pragma.domain.model.DetalleVenta;
import com.pragma.domain.model.Venta;
import com.pragma.infrastructure.input.rest.dto.DetalleVentaRequestDto;
import com.pragma.infrastructure.input.rest.dto.DetalleVentaResponseDto;
import com.pragma.infrastructure.input.rest.dto.VentaRequestDto;
import com.pragma.infrastructure.input.rest.dto.VentaResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Interfaz para el mapeo entre DTOs de la API REST y entidades de dominio.
 * Utiliza MapStruct para generar la implementación automáticamente.
 */
@Mapper(componentModel = "spring")
public interface VentaRestMapper {

    /**
     * Convierte un DTO de solicitud de venta a una entidad de dominio.
     *
     * @param ventaRequestDto El DTO de solicitud de venta.
     * @return La entidad de dominio Venta.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fecha", ignore = true)
    @Mapping(target = "total", ignore = true)
    Venta toVenta(VentaRequestDto ventaRequestDto);

    /**
     * Convierte un DTO de solicitud de detalle de venta a una entidad de dominio.
     *
     * @param detalleVentaRequestDto El DTO de solicitud de detalle de venta.
     * @return La entidad de dominio DetalleVenta.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nombreProducto", ignore = true)
    @Mapping(target = "precioUnitario", ignore = true)
    @Mapping(target = "subtotal", ignore = true)
    @Mapping(target = "stockRestante", ignore = true)
    DetalleVenta toDetalleVenta(DetalleVentaRequestDto detalleVentaRequestDto);

    /**
     * Convierte una entidad de dominio de venta a un DTO de respuesta.
     *
     * @param venta La entidad de dominio Venta.
     * @return El DTO de respuesta de venta.
     */
    VentaResponseDto toVentaResponseDto(Venta venta);

    /**
     * Convierte una entidad de dominio de detalle de venta a un DTO de respuesta.
     *
     * @param detalleVenta La entidad de dominio DetalleVenta.
     * @return El DTO de respuesta de detalle de venta.
     */
    DetalleVentaResponseDto toDetalleVentaResponseDto(DetalleVenta detalleVenta);

    /**
     * Convierte una lista de entidades de dominio de venta a una lista de DTOs de respuesta.
     *
     * @param ventas La lista de entidades de dominio Venta.
     * @return La lista de DTOs de respuesta de venta.
     */
    List<VentaResponseDto> toVentaResponseDtoList(List<Venta> ventas);
}
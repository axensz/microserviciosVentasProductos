package com.pragma.application.mapper;

import com.pragma.application.dto.DetalleVentaRequestDto;
import com.pragma.application.dto.DetalleVentaResponseDto;
import com.pragma.domain.model.DetalleVenta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping; // Added import
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DetalleVentaDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nombreProducto", ignore = true) // Will be enriched by use case
    @Mapping(target = "precioUnitario", ignore = true) // Will be enriched by use case
    @Mapping(target = "subtotal", ignore = true)       // Will be calculated by use case or domain
    @Mapping(target = "stockRestante", ignore = true)  // Informational, set by use case
    DetalleVenta toDomain(DetalleVentaRequestDto detalleVentaRequestDto);

    DetalleVentaResponseDto toResponse(DetalleVenta detalleVenta);
}

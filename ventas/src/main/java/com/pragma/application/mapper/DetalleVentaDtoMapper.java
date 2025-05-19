package com.pragma.application.mapper;

import com.pragma.application.dto.DetalleVentaRequestDto;
import com.pragma.application.dto.DetalleVentaResponseDto;
import com.pragma.domain.model.DetalleVenta;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DetalleVentaDtoMapper {

    DetalleVenta toDomain(DetalleVentaRequestDto detalleVentaRequestDto); // Renamed from toDetalleVenta

    DetalleVentaResponseDto toResponse(DetalleVenta detalleVenta); // Renamed from toDetalleVentaResponseDto
}

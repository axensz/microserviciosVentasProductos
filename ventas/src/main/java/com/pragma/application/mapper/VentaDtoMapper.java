package com.pragma.application.mapper;

import com.pragma.application.dto.VentaRequestDto;
import com.pragma.application.dto.VentaResponseDto;
import com.pragma.domain.model.Venta;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {DetalleVentaDtoMapper.class})
public interface VentaDtoMapper {

    Venta toDomain(VentaRequestDto ventaRequestDto);

    VentaResponseDto toResponse(Venta venta);

    List<VentaResponseDto> toResponseList(List<Venta> ventas);
}

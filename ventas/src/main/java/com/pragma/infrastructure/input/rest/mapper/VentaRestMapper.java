package com.pragma.infrastructure.input.rest.mapper;

import com.pragma.application.dto.VentaRequestDto;
import com.pragma.application.dto.VentaResponseDto;
import com.pragma.application.mapper.VentaDtoMapper;
import com.pragma.domain.model.Venta;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {VentaDtoMapper.class})
public interface VentaRestMapper {

    Venta toVenta(VentaRequestDto ventaRequestDto);

    VentaResponseDto toVentaResponseDto(Venta venta);

    List<VentaResponseDto> toVentaResponseDtoList(List<Venta> ventas);
}
package com.pragma.infrastructure.input.rest.mapper;

import com.pragma.application.dto.VentaRequestDto;
import com.pragma.application.dto.VentaResponseDto;
import com.pragma.domain.model.Venta;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
@DecoratedWith(VentaRestMapperDecorator.class)
public interface VentaRestMapper {

    // Map from request DTO to domain model
    Venta toVenta(VentaRequestDto ventaRequestDto);
    
    // These methods will be implemented by the decorator
    @Named("toVentaResponseDto")
    VentaResponseDto toVentaResponseDto(Venta venta);
    
    @Named("toVentaResponseDtoList")
    List<VentaResponseDto> toVentaResponseDtoList(List<Venta> ventas);
}
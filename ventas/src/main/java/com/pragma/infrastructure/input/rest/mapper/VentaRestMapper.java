package com.pragma.infrastructure.input.rest.mapper;

import com.pragma.application.dto.VentaRequestDto;
import com.pragma.application.dto.VentaResponseDto;
import com.pragma.application.mapper.VentaDtoMapper;
import com.pragma.domain.model.Venta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {VentaDtoMapper.class})
public interface VentaRestMapper {

    // Map from request DTO to domain model
    Venta toVenta(VentaRequestDto ventaRequestDto);
    
    // Delegate domain-to-dto mapping to VentaDtoMapper
    default VentaResponseDto toVentaResponseDto(Venta venta) {
        return getVentaDtoMapper().toResponse(venta);
    }
    
    default List<VentaResponseDto> toVentaResponseDtoList(List<Venta> ventas) {
        return getVentaDtoMapper().toResponseList(ventas);
    }
    
    // Abstract method to obtain VentaDtoMapper (will be implemented by MapStruct)
    VentaDtoMapper getVentaDtoMapper();
}
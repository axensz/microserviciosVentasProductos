package com.pragma.infrastructure.input.rest.mapper;

import com.pragma.application.dto.VentaResponseDto;
import com.pragma.application.mapper.VentaDtoMapper;
import com.pragma.domain.model.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Implementación de decorador para VentaRestMapper que delega
 * la conversión de objetos de dominio a DTOs en VentaDtoMapper.
 */
public abstract class VentaRestMapperDecorator implements VentaRestMapper {
    
    @Autowired
    @Qualifier("delegate")
    private VentaRestMapper delegate;
    
    @Autowired
    private VentaDtoMapper ventaDtoMapper;
    
    @Override
    public VentaResponseDto toVentaResponseDto(Venta venta) {
        return ventaDtoMapper.toResponse(venta);
    }
    
    @Override
    public List<VentaResponseDto> toVentaResponseDtoList(List<Venta> ventas) {
        return ventaDtoMapper.toResponseList(ventas);
    }
    
    @Override
    public Venta toVenta(VentaRequestDto ventaRequestDto) {
        // Delegate the implementation to the generated MapStruct code
        return delegate.toVenta(ventaRequestDto);
    }
}

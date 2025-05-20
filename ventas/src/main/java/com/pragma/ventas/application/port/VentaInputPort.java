package com.pragma.ventas.application.port;

import com.pragma.ventas.domain.model.Venta;
import com.pragma.ventas.infrastructure.input.dto.VentaRequestDto;

import java.time.LocalDate;
import java.util.List;

public interface VentaInputPort {
    Venta registrarVenta(VentaRequestDto ventaDto);
    
    List<Venta> obtenerVentas();
    
    Venta obtenerVentaPorId(Long id);
    
    List<Venta> obtenerVentasPorProducto(Long productoId);
    
    List<Venta> obtenerVentasPorFecha(LocalDate fecha);
} 
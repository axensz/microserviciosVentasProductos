package com.pragma.ventas.application.port;

import com.pragma.ventas.domain.model.Venta;
import com.pragma.ventas.infrastructure.input.dto.VentaRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface VentaInputPort {
    Venta registrarVenta(VentaRequestDto ventaDto);
    
    Page<Venta> obtenerVentas(Pageable pageable);
    
    Venta obtenerVentaPorId(Long id);
    
    Page<Venta> obtenerVentasPorProducto(Long productoId, Pageable pageable);
    
    Page<Venta> obtenerVentasPorFecha(LocalDate fecha, Pageable pageable);
} 
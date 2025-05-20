package com.pragma.ventas.domain.repository;

import com.pragma.ventas.domain.model.Venta;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VentaGateway {
    Venta guardarVenta(Venta venta);
    
    List<Venta> obtenerVentas();
    
    Optional<Venta> obtenerVentaPorId(Long id);
    
    List<Venta> obtenerVentasPorProducto(Long productoId);
    
    List<Venta> obtenerVentasPorFecha(LocalDate fecha);
} 
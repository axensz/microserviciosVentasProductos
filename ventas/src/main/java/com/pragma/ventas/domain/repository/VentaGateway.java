package com.pragma.ventas.domain.repository;

import com.pragma.ventas.domain.model.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VentaGateway {
    Venta guardarVenta(Venta venta);
    
    Page<Venta> obtenerVentas(Pageable pageable);
    
    Optional<Venta> obtenerVentaPorId(Long id);
    
    Page<Venta> obtenerVentasPorProducto(Long productoId, Pageable pageable);
    
    Page<Venta> obtenerVentasPorFecha(LocalDate fecha, Pageable pageable);
} 
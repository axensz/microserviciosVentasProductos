package com.pragma.ventas.domain.repository;

import com.pragma.ventas.domain.model.Venta;

public interface VentaGateway {
    Venta guardarVenta(Venta venta);
} 
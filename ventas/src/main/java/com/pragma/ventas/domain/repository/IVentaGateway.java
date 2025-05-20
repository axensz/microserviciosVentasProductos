package com.pragma.ventas.domain.repository;

import com.pragma.ventas.domain.model.Venta;

public interface IVentaGateway {
    Venta guardarVenta(Venta venta);
} 
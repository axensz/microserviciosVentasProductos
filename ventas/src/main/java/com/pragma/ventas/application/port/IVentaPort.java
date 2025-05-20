package com.pragma.ventas.application.port;

import com.pragma.ventas.domain.model.Venta;

public interface IVentaPort {
    Venta registrarVenta(Venta venta);
} 
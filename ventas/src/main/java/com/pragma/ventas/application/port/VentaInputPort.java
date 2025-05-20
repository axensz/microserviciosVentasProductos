package com.pragma.ventas.application.port;

import com.pragma.ventas.domain.model.Venta;

public interface VentaInputPort {
    Venta registrarVenta(Venta venta);
} 
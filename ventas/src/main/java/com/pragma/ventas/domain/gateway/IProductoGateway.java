package com.pragma.ventas.domain.gateway;

import com.pragma.ventas.domain.model.Producto;

public interface IProductoGateway {
    Producto obtenerProducto(Long id);
    boolean validarExistenciaProducto(Long id);
} 
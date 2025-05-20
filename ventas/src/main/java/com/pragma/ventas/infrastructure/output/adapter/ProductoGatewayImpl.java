package com.pragma.ventas.infrastructure.output.adapter;

import com.pragma.ventas.domain.gateway.IProductoGateway;
import com.pragma.ventas.domain.model.Producto;
import com.pragma.ventas.infrastructure.output.client.ProductoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductoGatewayImpl implements IProductoGateway {

    private final ProductoClient productoClient;

    @Override
    public Producto obtenerProducto(Long id) {
        return productoClient.obtenerProducto(id).getBody();
    }

    @Override
    public boolean validarExistenciaProducto(Long id) {
        return productoClient.validarExistenciaProducto(id).getBody();
    }
} 
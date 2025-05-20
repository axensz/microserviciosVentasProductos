package com.pragma.ventas.infrastructure.output.adapter;

import com.pragma.ventas.domain.repository.IProductoGateway;
import com.pragma.ventas.domain.model.Producto;
import com.pragma.ventas.infrastructure.output.client.ProductoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductoGatewayAdapter implements IProductoGateway {

    private final ProductoClient productoClient;

    @Override
    public Producto obtenerProducto(Long id) {
        ResponseEntity<Producto> response = productoClient.obtenerProducto(id);
        return response != null ? response.getBody() : null;
    }

    @Override
    public boolean validarExistenciaProducto(Long id) {
        ResponseEntity<Boolean> response = productoClient.validarExistenciaProducto(id);
        return Optional.ofNullable(response)
                      .map(ResponseEntity::getBody)
                      .orElse(false);
    }
} 
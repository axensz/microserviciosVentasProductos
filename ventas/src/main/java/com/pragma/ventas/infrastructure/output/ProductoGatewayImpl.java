package com.pragma.ventas.infrastructure.output;

import com.pragma.ventas.domain.model.Producto;
import com.pragma.ventas.domain.repository.IProductoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * Implementación del gateway de productos que se comunica con el microservicio de productos.
 * Esta clase utiliza RestTemplate para realizar las llamadas HTTP al servicio de productos.
 *
 * @version 1.0
 * @since 2025-05-20
 */
@Repository
@RequiredArgsConstructor
public class ProductoGatewayImpl implements IProductoGateway {

    private final RestTemplate restTemplate;
    private static final String PRODUCTOS_SERVICE_URL = "http://productos-app:8080/api/productos";

    /**
     * {@inheritDoc}
     * <p>
     * Realiza una llamada GET al servicio de productos para validar la existencia
     * de un producto específico.
     * </p>
     */
    @Override
    public boolean validarExistenciaProducto(Long productoId) {
        try {
            restTemplate.getForObject(PRODUCTOS_SERVICE_URL + "/{id}", Producto.class, productoId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Realiza una llamada GET al servicio de productos para obtener la información
     * detallada de un producto específico.
     * </p>
     *
     * @throws RuntimeException si hay un error en la comunicación con el servicio
     */
    @Override
    public Producto obtenerProducto(Long productoId) {
        try {
            return restTemplate.getForObject(PRODUCTOS_SERVICE_URL + "/{id}", Producto.class, productoId);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el producto: " + e.getMessage());
        }
    }
} 
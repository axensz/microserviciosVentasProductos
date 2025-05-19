package com.pragma.infrastructure.output.feign.adapter;

import com.pragma.application.exception.ProductoNotFoundException;
import com.pragma.application.exception.StockInsuficienteException;
import com.pragma.application.exception.ValidationException;
import com.pragma.application.port.output.ProductoServicePort;
import com.pragma.domain.model.Producto;
import com.pragma.infrastructure.output.feign.ProductoFeignClient;
import com.pragma.infrastructure.output.feign.dto.ProductoResponseDto;
import com.pragma.infrastructure.output.feign.mapper.ProductoFeignMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Adaptador que implementa {@link ProductoServicePort} utilizando Feign Client
 * para comunicarse con el microservicio de Productos.
 */
@Component
@RequiredArgsConstructor
public class ProductoFeignAdapter implements ProductoServicePort {

    private final ProductoFeignClient productoFeignClient;
    private final ProductoFeignMapper productoFeignMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public Producto obtenerProductoPorId(Long id) {
        if (id == null) {
            throw new ValidationException("El ID del producto no puede ser nulo.");
        }

        try {
            ProductoResponseDto productoResponseDto = productoFeignClient.obtenerProductoPorId(id).getBody();
            if (productoResponseDto == null) {
                throw new ProductoNotFoundException("Producto no encontrado con ID: " + id);
            }
            return productoFeignMapper.toProducto(productoResponseDto);
        } catch (FeignException e) {
            if (e.status() == HttpStatus.NOT_FOUND.value()) {
                throw new ProductoNotFoundException("Producto no encontrado con ID: " + id, e);
            }
            throw new RuntimeException("Error al comunicarse con el microservicio de Productos", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Producto actualizarStockProducto(Long id, Integer cantidad) {
        if (id == null || cantidad == null) {
            throw new ValidationException("El ID del producto y la cantidad no pueden ser nulos.");
        }

        try {
            ProductoResponseDto productoResponseDto = productoFeignClient.actualizarStockProducto(id, cantidad).getBody();
            if (productoResponseDto == null) {
                throw new ProductoNotFoundException("Producto no encontrado con ID: " + id);
            }
            return productoFeignMapper.toProducto(productoResponseDto);
        } catch (FeignException e) {
            if (e.status() == HttpStatus.NOT_FOUND.value()) {
                throw new ProductoNotFoundException("Producto no encontrado con ID: " + id, e);
            } else if (e.status() == HttpStatus.BAD_REQUEST.value()) {
                // Asumimos que un 400 es por stock insuficiente, pero en un caso real
                // deberíamos analizar el cuerpo de la respuesta para determinar el tipo de error
                Producto producto = obtenerProductoPorId(id);
                throw new StockInsuficienteException(
                        "Stock insuficiente para el producto con ID: " + id,
                        id,
                        producto.getStock(),
                        Math.abs(cantidad)
                );
            }
            throw new RuntimeException("Error al comunicarse con el microservicio de Productos", e);
        }
    }
}
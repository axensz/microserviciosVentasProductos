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
                // Esto sería inesperado si la llamada HTTP fue exitosa (ej. 200 OK) pero el cuerpo es nulo.
                // Podría indicar un problema con la respuesta del servicio de productos o la deserialización.
                throw new ProductoNotFoundException("Respuesta inesperada (cuerpo nulo) al actualizar stock para el producto con ID: " + id + ". El producto podría no existir o hubo un error en el servicio.");
            }
            return productoFeignMapper.toProducto(productoResponseDto);
        } catch (FeignException e) {
            if (e.status() == HttpStatus.NOT_FOUND.value()) {
                throw new ProductoNotFoundException("Producto no encontrado con ID: " + id + " durante la actualización de stock.", e);
            } else if (e.status() == HttpStatus.BAD_REQUEST.value()) {
                // El servicio de productos indicó una solicitud incorrecta durante la actualización de stock.
                // Esto puede ser por stock insuficiente (detectado por el servicio de productos tras el chequeo inicial en VentaUseCase)
                // o por una cantidad inválida según las reglas del servicio de productos.
                // VentaUseCase ya realiza una verificación previa de stock.
                throw new RuntimeException(
                        "La actualización de stock para el producto ID: " + id + " falló (Bad Request desde el servicio de productos). " +
                        "Causa probable: stock insuficiente o cantidad inválida. Detalles del error original: " + e.getMessage(), e
                );
            }
            // Para otros errores de Feign (ej. problemas de comunicación, timeouts, 5xx)
            throw new RuntimeException("Error al comunicarse con el microservicio de Productos durante la actualización de stock para el ID: " + id, e);
        }
    }
}
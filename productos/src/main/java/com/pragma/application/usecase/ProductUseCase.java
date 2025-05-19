package com.pragma.application.usecase;

import com.pragma.application.exception.ProductoNotFoundException;
import com.pragma.application.exception.StockUpdateException;
import com.pragma.application.port.input.ProductInputPort;
import com.pragma.domain.model.Producto;
import com.pragma.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de {@link ProductInputPort} que define los casos de uso para la gestión de productos.
 * Esta clase contiene la lógica de negocio principal relacionada con las operaciones de productos,
 * interactuando con la capa de persistencia a través de {@link ProductRepository}.
 */
@Service
@RequiredArgsConstructor
public class ProductUseCase implements ProductInputPort {

    private final ProductRepository productRepository;

    /**
     * Crea un nuevo producto en el sistema.
     *
     * @param producto El objeto {@link Producto} a crear. No debe ser nulo y debe cumplir con las validaciones básicas.
     * @return El producto creado, usualmente con su ID asignado por el sistema de persistencia.
     * @throws ValidationException Si el producto es nulo o sus datos no son válidos (nombre, precio, stock).
     */
    @Override
    @Transactional
    public Producto crearProducto(Producto producto) {
        if (producto == null) {
            throw new com.pragma.application.exception.ValidationException("El producto no puede ser nulo.");
        }
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new com.pragma.application.exception.ValidationException("El nombre del producto es obligatorio.");
        }
        if (producto.getPrecio() == null || producto.getPrecio() <= 0) {
            throw new com.pragma.application.exception.ValidationException("El precio del producto debe ser mayor que cero.");
        }
        if (producto.getStock() == null || producto.getStock() < 0) {
            throw new com.pragma.application.exception.ValidationException("El stock inicial del producto no puede ser negativo.");
        }
        return productRepository.save(producto);
    }

    /**
     * Obtiene un producto específico por su identificador único.
     *
     * @param id El ID del producto a obtener. No debe ser nulo.
     * @return El {@link Producto} encontrado.
     * @throws ValidationException Si el ID del producto es nulo.
     * @throws ProductoNotFoundException Si no se encuentra el producto con el ID especificado.
     */
    @Override
    @Transactional(readOnly = true)
    public Producto obtenerProductoPorId(Long id) {
        if (id == null) {
            throw new com.pragma.application.exception.ValidationException("El ID del producto no puede ser nulo.");
        }
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con ID: " + id));
    }

    /**
     * Obtiene una lista de todos los productos registrados en el sistema.
     *
     * @return Una lista de {@link Producto}. La lista puede estar vacía si no hay productos.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Producto> obtenerTodosLosProductos() {
        return productRepository.findAll();
    }

    /**
     * Actualiza la información completa de un producto existente.
     *
     * @param id El ID del producto a actualizar. No debe ser nulo.
     * @param productoActualizado El objeto {@link Producto} con la nueva información. No debe ser nulo.
     * @return El producto actualizado.
     * @throws ValidationException Si el ID o el producto son nulos, o si los datos del producto actualizado no son válidos.
     * @throws ProductoNotFoundException Si no se encuentra el producto con el ID especificado para actualizar.
     */
    @Override
    @Transactional
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        if (id == null || productoActualizado == null) {
            throw new com.pragma.application.exception.ValidationException("El ID y el producto para actualizar no pueden ser nulos.");
        }
        Producto productoExistente = productRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con ID: " + id + " para actualizar."));

        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productoExistente.setStock(productoActualizado.getStock());

        if (productoExistente.getNombre() == null || productoExistente.getNombre().trim().isEmpty()) {
            throw new com.pragma.application.exception.ValidationException("El nombre del producto es obligatorio.");
        }
        if (productoExistente.getPrecio() == null || productoExistente.getPrecio() <= 0) {
            throw new com.pragma.application.exception.ValidationException("El precio del producto debe ser mayor que cero.");
        }
        if (productoExistente.getStock() == null || productoExistente.getStock() < 0) {
            throw new com.pragma.application.exception.ValidationException("El stock del producto no puede ser negativo.");
        }

        return productRepository.save(productoExistente);
    }

    /**
     * Actualiza el stock de un producto específico sumando o restando una cantidad.
     *
     * @param id El ID del producto cuyo stock se va a actualizar. No debe ser nulo.
     * @param cantidad La cantidad a sumar o restar del stock (positiva para sumar, negativa para restar).
     * @return El producto con el stock actualizado.
     * @throws ValidationException Si el ID o la cantidad son nulos.
     * @throws StockUpdateException Si el stock resultante es negativo.
     * @throws ProductoNotFoundException Si no se encuentra el producto con el ID especificado para actualizar el stock.
     */
    @Override
    @Transactional
    public Producto actualizarStockProducto(Long id, Integer cantidad) {
        if (id == null || cantidad == null) {
            throw new com.pragma.application.exception.ValidationException("El ID y la cantidad no pueden ser nulos.");
        }

        Producto producto = productRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con ID: " + id + " para actualizar stock."));

        int stockActual = producto.getStock() != null ? producto.getStock() : 0;
        int stockNuevo = stockActual + cantidad;
        if (stockNuevo < 0) {
            throw new StockUpdateException("El stock resultante no puede ser negativo.");
        }
        producto.setStock(stockNuevo);
        return productRepository.save(producto);
    }

    /**
     * Elimina un producto del sistema basado en su ID.
     *
     * @param id El ID del producto a eliminar. No debe ser nulo.
     * @throws ValidationException Si el ID del producto es nulo.
     * @throws ProductoNotFoundException Si no se encuentra el producto con el ID especificado para eliminar.
     */
    @Override
    @Transactional
    public void eliminarProductoPorId(Long id) {
        if (id == null) {
            throw new com.pragma.application.exception.ValidationException("El ID del producto no puede ser nulo.");
        }
        if (!productRepository.existsById(id)) {
            throw new ProductoNotFoundException("Producto no encontrado con ID: " + id + " para eliminar.");
        }
        productRepository.deleteById(id);
    }
}

package com.pragma.application.port.output;

import com.pragma.domain.model.Producto;

/**
 * Puerto de salida para interactuar con el microservicio de Productos.
 * Define las operaciones que la capa de aplicación necesita del microservicio de Productos.
 * Las implementaciones de esta interfaz se encargarán de la comunicación con el microservicio.
 */
public interface ProductoServicePort {

    /**
     * Obtiene un producto por su ID.
     * Este método se utiliza para validar la existencia del producto y su stock disponible.
     *
     * @param id El ID del producto a obtener.
     * @return El producto encontrado.
     * @throws com.pragma.application.exception.ValidationException Si el ID del producto es nulo.
     * @throws com.pragma.application.exception.ProductoNotFoundException Si no se encuentra el producto con el ID especificado.
     */
    Producto obtenerProductoPorId(Long id);

    /**
     * Actualiza el stock de un producto.
     * Este método se utiliza para decrementar el stock después de una venta.
     *
     * @param id El ID del producto cuyo stock se va a actualizar.
     * @param cantidad La cantidad a sumar o restar del stock (positiva para sumar, negativa para restar).
     * @return El producto con el stock actualizado.
     * @throws com.pragma.application.exception.ValidationException Si el ID del producto o la cantidad son nulos.
     * @throws com.pragma.application.exception.ProductoNotFoundException Si no se encuentra el producto con el ID especificado.
     * @throws com.pragma.application.exception.StockInsuficienteException Si el stock resultante es negativo.
     */
    Producto actualizarStockProducto(Long id, Integer cantidad);
}
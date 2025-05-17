package com.pragma.application.port.input;

import com.pragma.domain.model.Producto;
import java.util.List;

/**
 * Puerto de entrada para la gestión de productos.
 * Define los casos de uso que la aplicación ofrece para interactuar con los productos.
 * Las implementaciones de esta interfaz orquestarán la lógica de negocio
 * y la interacción con los puertos de salida (repositorios).
 */
public interface ProductInputPort {

    /**
     * Crea un nuevo producto en el sistema.
     *
     * @param producto El objeto {@link Producto} a crear. No debe ser nulo.
     * @return El producto creado, usualmente con su ID asignado por el sistema de persistencia.
     * @throws com.pragma.application.exception.ValidationException Si los datos del producto no son válidos.
     */
    Producto crearProducto(Producto producto);

    /**
     * Obtiene un producto específico por su identificador único.
     *
     * @param id El ID del producto a obtener.
     * @return El {@link Producto} encontrado.
     * @throws com.pragma.application.exception.ProductoNotFoundException Si no se encuentra el producto con el ID especificado.
     */
    Producto obtenerProductoPorId(Long id);

    /**
     * Obtiene una lista de todos los productos registrados en el sistema.
     *
     * @return Una lista de {@link Producto}. La lista puede estar vacía si no hay productos.
     */
    List<Producto> obtenerTodosLosProductos();

    /**
     * Actualiza la información completa de un producto existente.
     *
     * @param id El ID del producto a actualizar.
     * @param producto El objeto {@link Producto} con la nueva información. No debe ser nulo.
     * @return El producto actualizado.
     * @throws com.pragma.application.exception.ProductoNotFoundException Si no se encuentra el producto con el ID especificado.
     * @throws com.pragma.application.exception.ValidationException Si los datos del producto no son válidos.
     */
    Producto actualizarProducto(Long id, Producto producto);

    /**
     * Actualiza el stock de un producto específico.
     *
     * @param id El ID del producto cuyo stock se va a actualizar.
     * @param nuevoStock La nueva cantidad de stock para el producto. Debe ser un valor no negativo.
     * @return El producto con el stock actualizado.
     * @throws com.pragma.application.exception.ProductoNotFoundException Si no se encuentra el producto con el ID especificado.
     * @throws com.pragma.application.exception.StockUpdateException Si el nuevo stock no es válido (ej. negativo).
     */
    Producto actualizarStockProducto(Long id, Integer nuevoStock);

    /**
     * Elimina un producto del sistema basado en su ID.
     *
     * @param id El ID del producto a eliminar.
     * @throws com.pragma.application.exception.ProductoNotFoundException Si no se encuentra el producto con el ID especificado.
     */
    void eliminarProductoPorId(Long id);
}

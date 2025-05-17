package com.pragma.domain.repository;

import com.pragma.domain.model.Producto;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define el contrato para las operaciones de persistencia
 * de la entidad {@link Producto}.
 * Esta interfaz actúa como un puerto de salida en la arquitectura limpia,
 * permitiendo a la capa de aplicación interactuar con el mecanismo de
 * almacenamiento de datos de forma agnóstica a la implementación.
 */
public interface ProductRepository {

    /**
     * Busca y devuelve un producto por su identificador único.
     *
     * @param id El identificador único del producto a buscar.
     * @return Un {@link Optional} que contiene el producto si se encuentra,
     *         o un {@link Optional#empty()} si no existe un producto con el id proporcionado.
     */
    Optional<Producto> findById(Long id);

    /**
     * Recupera una lista de todos los productos existentes.
     *
     * @return Una {@link List} de objetos {@link Producto}.
     *         La lista estará vacía si no hay productos.
     */
    List<Producto> findAll();

    /**
     * Guarda un nuevo producto o actualiza uno existente en el sistema de persistencia.
     * Si el producto tiene un ID nulo, se considera una nueva entidad y se creará.
     * Si el producto tiene un ID existente, se actualizará la entidad correspondiente.
     *
     * @param producto El objeto {@link Producto} a guardar o actualizar. No debe ser nulo.
     * @return El producto guardado o actualizado. Esto es útil ya que la operación
     *         de guardado puede modificar el objeto producto (por ejemplo, asignando un ID).
     */
    Producto save(Producto producto);

    /**
     * Elimina un producto del sistema de persistencia basado en su identificador único.
     * Si no existe un producto con el ID proporcionado, la operación no tendrá efecto
     * y no lanzará una excepción.
     *
     * @param id El identificador único del producto a eliminar.
     */
    void deleteById(Long id);

    /**
     * Verifica si existe un producto con el identificador único especificado.
     *
     * @param id El identificador único del producto a verificar.
     * @return {@code true} si un producto con el id especificado existe, {@code false} en caso contrario.
     */
    boolean existsById(Long id);
}

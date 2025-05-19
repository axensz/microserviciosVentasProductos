package com.pragma.domain.repository;

import com.pragma.domain.model.Venta;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define el contrato para las operaciones de persistencia
 * de la entidad {@link Venta}.
 * Esta interfaz actúa como un puerto de salida en la arquitectura limpia,
 * permitiendo a la capa de aplicación interactuar con el mecanismo de
 * almacenamiento de datos de forma agnóstica a la implementación.
 */
public interface VentaRepository {

    /**
     * Busca y devuelve una venta por su identificador único.
     *
     * @param id El identificador único de la venta a buscar.
     * @return Un {@link Optional} que contiene la venta si se encuentra,
     *         o un {@link Optional#empty()} si no existe una venta con el id proporcionado.
     */
    Optional<Venta> findById(Long id);

    /**
     * Recupera una lista de todas las ventas existentes.
     *
     * @return Una {@link List} de objetos {@link Venta}.
     *         La lista estará vacía si no hay ventas.
     */
    List<Venta> findAll();

    /**
     * Guarda una nueva venta en el sistema de persistencia.
     *
     * @param venta El objeto {@link Venta} a guardar. No debe ser nulo.
     * @return La venta guardada. Esto es útil ya que la operación
     *         de guardado puede modificar el objeto venta (por ejemplo, asignando un ID).
     */
    Venta save(Venta venta);
}
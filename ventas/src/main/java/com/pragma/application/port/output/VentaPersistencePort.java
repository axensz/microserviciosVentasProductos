package com.pragma.application.port.output;

import com.pragma.domain.model.Venta;
import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida para la persistencia de ventas.
 * Define las operaciones que la capa de aplicación necesita para interactuar
 * con el mecanismo de almacenamiento de datos de ventas.
 * Las implementaciones de esta interfaz serán responsables de la lógica de
 * acceso a datos (por ejemplo, usando JPA, JDBC, etc.).
 */
public interface VentaPersistencePort {

    /**
     * Guarda una nueva venta en el sistema de persistencia.
     *
     * @param venta El objeto {@link Venta} a guardar. No debe ser nulo.
     * @return La venta guardada, que puede incluir un ID generado por la base de datos.
     */
    Venta save(Venta venta);

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
}

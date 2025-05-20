package com.pragma.ventas.infrastructure.output.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repositorio JPA para la entidad VentaEntity.
 * Proporciona métodos para acceder y manipular los datos de ventas en la base de datos.
 *
 * @version 1.0
 * @since 2025-05-20
 */
@Repository
public interface VentaJpaRepository extends JpaRepository<VentaEntity, Long> {
    
    /**
     * Busca todas las ventas que incluyen un producto específico.
     *
     * @param productoId ID del producto a buscar
     * @return Lista de ventas que incluyen el producto
     */
    List<VentaEntity> findByDetallesProductoId(Long productoId);

    /**
     * Busca todas las ventas realizadas en una fecha específica.
     *
     * @param fecha Fecha para filtrar las ventas
     * @return Lista de ventas realizadas en la fecha especificada
     */
    List<VentaEntity> findByFecha(LocalDate fecha);
} 
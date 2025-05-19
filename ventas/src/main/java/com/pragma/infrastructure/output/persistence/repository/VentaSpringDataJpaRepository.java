package com.pragma.infrastructure.output.persistence.repository;

import com.pragma.infrastructure.output.persistence.entity.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio Spring Data JPA para la entidad {@link VentaEntity}.
 * Proporciona métodos para realizar operaciones CRUD en la base de datos.
 */
@Repository
public interface VentaSpringDataJpaRepository extends JpaRepository<VentaEntity, Long> {
}
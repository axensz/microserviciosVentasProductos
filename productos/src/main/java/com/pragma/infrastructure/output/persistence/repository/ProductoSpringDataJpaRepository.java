package com.pragma.infrastructure.output.persistence.repository;

import com.pragma.infrastructure.output.persistence.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de Spring Data JPA para la entidad {@link ProductoEntity}.
 * Extiende {@link JpaRepository}, lo que proporciona automáticamente métodos CRUD
 * (Create, Read, Update, Delete) y otras funcionalidades de consulta comunes
 * sin necesidad de implementación explícita.
 *
 * <p>Spring Data JPA generará la implementación de esta interfaz en tiempo de ejecución.</p>
 *
 * <p>Esta interfaz será utilizada por el adaptador {@code PostgresProductRepositoryAdapter}
 * para interactuar con la base de datos PostgreSQL a través de JPA.</p>
 */
@Repository // Indica a Spring que esta interfaz es un bean de repositorio.
public interface ProductoSpringDataJpaRepository extends JpaRepository<ProductoEntity, Long> {
    // Spring Data JPA proporciona automáticamente los métodos CRUD básicos:
    // - save(S entity)
    // - findById(ID id)
    // - findAll()
    // - deleteById(ID id)
    // - existsById(ID id)
    // - count()
    // ... y más.

    // Se pueden añadir métodos de consulta personalizados aquí si son necesarios,
    // siguiendo las convenciones de nomenclatura de Spring Data JPA (query methods)
    // Por ejemplo: List<ProductoEntity> findByNombre(String nombre);
}

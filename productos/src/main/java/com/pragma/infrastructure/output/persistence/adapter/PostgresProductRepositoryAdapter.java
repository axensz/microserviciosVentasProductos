package com.pragma.infrastructure.output.persistence.adapter;

import com.pragma.domain.model.Producto;
import com.pragma.domain.repository.ProductRepository;
import com.pragma.infrastructure.output.persistence.entity.ProductoEntity;
import com.pragma.infrastructure.output.persistence.mapper.ProductoPersistenceMapper;
import com.pragma.infrastructure.output.persistence.repository.ProductoSpringDataJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Adaptador que implementa la interfaz {@link ProductRepository}
 * utilizando Spring Data JPA para la persistencia de datos en PostgreSQL.
 * Esta clase se encarga de interactuar con {@link ProductoSpringDataJpaRepository}
 * y de mapear entre los objetos de dominio {@link Producto} y las entidades
 * de persistencia {@link ProductoEntity} mediante {@link ProductoPersistenceMapper}.
 */
@Repository // Marca esta clase como un bean de repositorio de Spring.
public class PostgresProductRepositoryAdapter implements ProductRepository {

    private final ProductoSpringDataJpaRepository jpaRepository;
    private final ProductoPersistenceMapper productoPersistenceMapper; // Corregido el nombre

    /**
     * Constructor para la inyección de dependencias.
     *
     * @param jpaRepository El repositorio Spring Data JPA para las operaciones de base de datos.
     * @param productoPersistenceMapper El mapper para convertir entre entidades y objetos de dominio.
     */
    public PostgresProductRepositoryAdapter(ProductoSpringDataJpaRepository jpaRepository, ProductoPersistenceMapper productoPersistenceMapper) {
        this.jpaRepository = jpaRepository;
        this.productoPersistenceMapper = productoPersistenceMapper; // Corregido el nombre
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Producto> findById(Long id) {
        Optional<ProductoEntity> productoEntityOptional = jpaRepository.findById(id);
        return productoEntityOptional.map(productoPersistenceMapper::toProducto); // Corregido el nombre
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Producto> findAll() {
        List<ProductoEntity> productoEntities = jpaRepository.findAll();
        return productoPersistenceMapper.toProductoList(productoEntities); // Corregido el nombre
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Producto save(Producto producto) {
        ProductoEntity productoEntity = productoPersistenceMapper.toProductoEntity(producto);
        ProductoEntity savedEntity = jpaRepository.save(productoEntity);
        return productoPersistenceMapper.toProducto(savedEntity); // Corregido el nombre
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
}

package com.pragma.infrastructure.output.persistence.adapter;

import com.pragma.domain.model.Producto;
import com.pragma.infrastructure.output.persistence.entity.ProductoEntity;
import com.pragma.infrastructure.output.persistence.mapper.ProductoPersistenceMapper;
import com.pragma.infrastructure.output.persistence.repository.ProductoSpringDataJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostgresProductRepositoryAdapterTest {

    @Mock
    private ProductoSpringDataJpaRepository jpaRepository;

    @Mock
    private ProductoPersistenceMapper mapper;

    private PostgresProductRepositoryAdapter repositoryAdapter;

    private Producto producto;
    private ProductoEntity productoEntity;

    @BeforeEach
    void setUp() {
        repositoryAdapter = new PostgresProductRepositoryAdapter(jpaRepository, mapper);
        
        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Test Product");
        producto.setDescripcion("Test Description");
        producto.setPrecio(100.0);
        producto.setStock(10);

        productoEntity = new ProductoEntity();
        productoEntity.setId(1L);
        productoEntity.setNombre("Test Product");
        productoEntity.setDescripcion("Test Description");
        productoEntity.setPrecio(100.0);
        productoEntity.setStock(10);
    }

    @Test
    void save_ValidProduct_ShouldSaveSuccessfully() {
        when(mapper.toProductoEntity(any(Producto.class))).thenReturn(productoEntity);
        when(jpaRepository.save(any(ProductoEntity.class))).thenReturn(productoEntity);
        when(mapper.toProducto(any(ProductoEntity.class))).thenReturn(producto);

        Producto result = repositoryAdapter.save(producto);

        assertNotNull(result);
        assertEquals(producto.getId(), result.getId());
        verify(jpaRepository).save(any(ProductoEntity.class));
    }

    @Test
    void findById_ExistingProduct_ShouldReturnProduct() {
        when(jpaRepository.findById(1L)).thenReturn(Optional.of(productoEntity));
        when(mapper.toProducto(any(ProductoEntity.class))).thenReturn(producto);

        Optional<Producto> result = repositoryAdapter.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(producto.getId(), result.get().getId());
    }

    @Test
    void findAll_ShouldReturnAllProducts() {
        List<ProductoEntity> entities = Arrays.asList(productoEntity);
        List<Producto> productos = Arrays.asList(producto);

        when(jpaRepository.findAll()).thenReturn(entities);
        when(mapper.toProductoList(any())).thenReturn(productos);

        List<Producto> result = repositoryAdapter.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(producto.getId(), result.get(0).getId());
    }

    @Test
    void deleteById_ShouldDeleteSuccessfully() {
        doNothing().when(jpaRepository).deleteById(1L);

        assertDoesNotThrow(() -> repositoryAdapter.deleteById(1L));
        verify(jpaRepository).deleteById(1L);
    }

    @Test
    void existsById_ExistingProduct_ShouldReturnTrue() {
        when(jpaRepository.existsById(1L)).thenReturn(true);

        boolean result = repositoryAdapter.existsById(1L);

        assertTrue(result);
    }
} 
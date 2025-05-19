package com.pragma.infrastructure.output.persistence.mapper;

import com.pragma.domain.model.Producto;
import com.pragma.infrastructure.output.persistence.entity.ProductoEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductoPersistenceMapperTest {

    private final ProductoPersistenceMapper mapper = Mappers.getMapper(ProductoPersistenceMapper.class);

    @Test
    void toProducto_ShouldMapEntityToDomain() {
        // Arrange
        ProductoEntity entity = new ProductoEntity();
        entity.setId(1L);
        entity.setNombre("Test Product");
        entity.setDescripcion("Test Description");
        entity.setPrecio(100.0);
        entity.setStock(10);

        // Act
        Producto producto = mapper.toProducto(entity);

        // Assert
        assertNotNull(producto);
        assertEquals(entity.getId(), producto.getId());
        assertEquals(entity.getNombre(), producto.getNombre());
        assertEquals(entity.getDescripcion(), producto.getDescripcion());
        assertEquals(entity.getPrecio(), producto.getPrecio());
        assertEquals(entity.getStock(), producto.getStock());
    }

    @Test
    void toProductoEntity_ShouldMapDomainToEntity() {
        // Arrange
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Test Product");
        producto.setDescripcion("Test Description");
        producto.setPrecio(100.0);
        producto.setStock(10);

        // Act
        ProductoEntity entity = mapper.toProductoEntity(producto);

        // Assert
        assertNotNull(entity);
        assertEquals(producto.getId(), entity.getId());
        assertEquals(producto.getNombre(), entity.getNombre());
        assertEquals(producto.getDescripcion(), entity.getDescripcion());
        assertEquals(producto.getPrecio(), entity.getPrecio());
        assertEquals(producto.getStock(), entity.getStock());
    }

    @Test
    void toProductoList_ShouldMapEntityListToDomainList() {
        // Arrange
        ProductoEntity entity1 = new ProductoEntity();
        entity1.setId(1L);
        entity1.setNombre("Product 1");
        entity1.setDescripcion("Description 1");
        entity1.setPrecio(100.0);
        entity1.setStock(10);

        ProductoEntity entity2 = new ProductoEntity();
        entity2.setId(2L);
        entity2.setNombre("Product 2");
        entity2.setDescripcion("Description 2");
        entity2.setPrecio(200.0);
        entity2.setStock(20);

        List<ProductoEntity> entities = Arrays.asList(entity1, entity2);

        // Act
        List<Producto> productos = mapper.toProductoList(entities);

        // Assert
        assertNotNull(productos);
        assertEquals(2, productos.size());
        
        Producto producto1 = productos.get(0);
        assertEquals(entity1.getId(), producto1.getId());
        assertEquals(entity1.getNombre(), producto1.getNombre());
        assertEquals(entity1.getDescripcion(), producto1.getDescripcion());
        assertEquals(entity1.getPrecio(), producto1.getPrecio());
        assertEquals(entity1.getStock(), producto1.getStock());

        Producto producto2 = productos.get(1);
        assertEquals(entity2.getId(), producto2.getId());
        assertEquals(entity2.getNombre(), producto2.getNombre());
        assertEquals(entity2.getDescripcion(), producto2.getDescripcion());
        assertEquals(entity2.getPrecio(), producto2.getPrecio());
        assertEquals(entity2.getStock(), producto2.getStock());
    }

    @Test
    void toProductoEntityList_ShouldMapDomainListToEntityList() {
        // Arrange
        Producto producto1 = new Producto();
        producto1.setId(1L);
        producto1.setNombre("Product 1");
        producto1.setDescripcion("Description 1");
        producto1.setPrecio(100.0);
        producto1.setStock(10);

        Producto producto2 = new Producto();
        producto2.setId(2L);
        producto2.setNombre("Product 2");
        producto2.setDescripcion("Description 2");
        producto2.setPrecio(200.0);
        producto2.setStock(20);

        List<Producto> productos = Arrays.asList(producto1, producto2);

        // Act
        List<ProductoEntity> entities = mapper.toProductoEntityList(productos);

        // Assert
        assertNotNull(entities);
        assertEquals(2, entities.size());
        
        ProductoEntity entity1 = entities.get(0);
        assertEquals(producto1.getId(), entity1.getId());
        assertEquals(producto1.getNombre(), entity1.getNombre());
        assertEquals(producto1.getDescripcion(), entity1.getDescripcion());
        assertEquals(producto1.getPrecio(), entity1.getPrecio());
        assertEquals(producto1.getStock(), entity1.getStock());

        ProductoEntity entity2 = entities.get(1);
        assertEquals(producto2.getId(), entity2.getId());
        assertEquals(producto2.getNombre(), entity2.getNombre());
        assertEquals(producto2.getDescripcion(), entity2.getDescripcion());
        assertEquals(producto2.getPrecio(), entity2.getPrecio());
        assertEquals(producto2.getStock(), entity2.getStock());
    }

    @Test
    void toProducto_WithNullEntity_ShouldReturnNull() {
        // Act
        Producto producto = mapper.toProducto(null);

        // Assert
        assertNull(producto);
    }

    @Test
    void toProducto_WithNullFields_ShouldMapCorrectly() {
        // Arrange
        ProductoEntity entity = new ProductoEntity();
        entity.setId(1L);
        entity.setNombre(null);
        entity.setDescripcion(null);
        entity.setPrecio(null);
        entity.setStock(null);

        // Act
        Producto producto = mapper.toProducto(entity);

        // Assert
        assertNotNull(producto);
        assertEquals(entity.getId(), producto.getId());
        assertNull(producto.getNombre());
        assertNull(producto.getDescripcion());
        assertNull(producto.getPrecio());
        assertNull(producto.getStock());
    }

    @Test
    void toProductoEntity_WithNullProduct_ShouldReturnNull() {
        // Act
        ProductoEntity entity = mapper.toProductoEntity(null);

        // Assert
        assertNull(entity);
    }

    @Test
    void toProductoEntity_WithNullFields_ShouldMapCorrectly() {
        // Arrange
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre(null);
        producto.setDescripcion(null);
        producto.setPrecio(null);
        producto.setStock(null);

        // Act
        ProductoEntity entity = mapper.toProductoEntity(producto);

        // Assert
        assertNotNull(entity);
        assertEquals(producto.getId(), entity.getId());
        assertNull(entity.getNombre());
        assertNull(entity.getDescripcion());
        assertNull(entity.getPrecio());
        assertNull(entity.getStock());
    }

    @Test
    void toProductoList_WithNullList_ShouldReturnNull() {
        // Act
        List<Producto> productos = mapper.toProductoList(null);

        // Assert
        assertNull(productos);
    }

    @Test
    void toProductoList_WithEmptyList_ShouldReturnEmptyList() {
        // Act
        List<Producto> productos = mapper.toProductoList(List.of());

        // Assert
        assertNotNull(productos);
        assertTrue(productos.isEmpty());
    }

    @Test
    void toProductoList_WithNullElements_ShouldMapCorrectly() {
        // Arrange
        List<ProductoEntity> entities = Arrays.asList(null, new ProductoEntity());

        // Act
        List<Producto> productos = mapper.toProductoList(entities);

        // Assert
        assertNotNull(productos);
        assertEquals(2, productos.size());
        assertNull(productos.get(0));
        assertNotNull(productos.get(1));
    }

    @Test
    void toProductoEntityList_WithNullList_ShouldReturnNull() {
        // Act
        List<ProductoEntity> entities = mapper.toProductoEntityList(null);

        // Assert
        assertNull(entities);
    }

    @Test
    void toProductoEntityList_WithEmptyList_ShouldReturnEmptyList() {
        // Act
        List<ProductoEntity> entities = mapper.toProductoEntityList(List.of());

        // Assert
        assertNotNull(entities);
        assertTrue(entities.isEmpty());
    }

    @Test
    void toProductoEntityList_WithNullElements_ShouldMapCorrectly() {
        // Arrange
        List<Producto> productos = Arrays.asList(null, new Producto());

        // Act
        List<ProductoEntity> entities = mapper.toProductoEntityList(productos);

        // Assert
        assertNotNull(entities);
        assertEquals(2, entities.size());
        assertNull(entities.get(0));
        assertNotNull(entities.get(1));
    }
} 
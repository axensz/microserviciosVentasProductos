package com.pragma.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {

    @Test
    void constructorVacio_ShouldCreateEmptyProduct() {
        // Act
        Producto producto = new Producto();

        // Assert
        assertNotNull(producto);
        assertNull(producto.getId());
        assertNull(producto.getNombre());
        assertNull(producto.getDescripcion());
        assertNull(producto.getPrecio());
        assertNull(producto.getStock());
    }

    @Test
    void constructorCompleto_ShouldCreateProductWithAllFields() {
        // Arrange
        Long id = 1L;
        String nombre = "Test Product";
        String descripcion = "Test Description";
        Double precio = 100.0;
        Integer stock = 10;

        // Act
        Producto producto = new Producto(id, nombre, descripcion, precio, stock);

        // Assert
        assertNotNull(producto);
        assertEquals(id, producto.getId());
        assertEquals(nombre, producto.getNombre());
        assertEquals(descripcion, producto.getDescripcion());
        assertEquals(precio, producto.getPrecio());
        assertEquals(stock, producto.getStock());
    }

    @Test
    void settersAndGetters_ShouldWorkCorrectly() {
        // Arrange
        Producto producto = new Producto();
        Long id = 1L;
        String nombre = "Test Product";
        String descripcion = "Test Description";
        Double precio = 100.0;
        Integer stock = 10;

        // Act
        producto.setId(id);
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);

        // Assert
        assertEquals(id, producto.getId());
        assertEquals(nombre, producto.getNombre());
        assertEquals(descripcion, producto.getDescripcion());
        assertEquals(precio, producto.getPrecio());
        assertEquals(stock, producto.getStock());
    }

    @Test
    void settersAndGetters_ShouldHandleNullValues() {
        // Arrange
        Producto producto = new Producto(1L, "Test", "Test", 100.0, 10);

        // Act
        producto.setId(null);
        producto.setNombre(null);
        producto.setDescripcion(null);
        producto.setPrecio(null);
        producto.setStock(null);

        // Assert
        assertNull(producto.getId());
        assertNull(producto.getNombre());
        assertNull(producto.getDescripcion());
        assertNull(producto.getPrecio());
        assertNull(producto.getStock());
    }
} 
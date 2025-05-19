package com.pragma.application.usecase;

import com.pragma.application.exception.ProductoNotFoundException;
import com.pragma.application.exception.StockUpdateException;
import com.pragma.application.exception.ValidationException;
import com.pragma.domain.model.Producto;
import com.pragma.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductUseCaseTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductUseCase productUseCase;

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Test Product");
        producto.setDescripcion("Test Description");
        producto.setPrecio(100.0);
        producto.setStock(10);
    }

    @Test
    void crearProducto_ValidProduct_ShouldCreateSuccessfully() {
        when(productRepository.save(any(Producto.class))).thenReturn(producto);

        Producto result = productUseCase.crearProducto(producto);

        assertNotNull(result);
        assertEquals(producto.getNombre(), result.getNombre());
        verify(productRepository).save(any(Producto.class));
    }

    @Test
    void crearProducto_NullProduct_ShouldThrowValidationException() {
        assertThrows(ValidationException.class, () -> productUseCase.crearProducto(null));
    }

    @Test
    void crearProducto_NombreNulo_ShouldThrowValidationException() {
        producto.setNombre(null);
        assertThrows(ValidationException.class, () -> productUseCase.crearProducto(producto));
    }

    @Test
    void crearProducto_NombreVacio_ShouldThrowValidationException() {
        producto.setNombre("   ");
        assertThrows(ValidationException.class, () -> productUseCase.crearProducto(producto));
    }

    @Test
    void crearProducto_PrecioNulo_ShouldThrowValidationException() {
        producto.setPrecio(null);
        assertThrows(ValidationException.class, () -> productUseCase.crearProducto(producto));
    }

    @Test
    void crearProducto_PrecioMenorOIgualACero_ShouldThrowValidationException() {
        producto.setPrecio(0.0);
        assertThrows(ValidationException.class, () -> productUseCase.crearProducto(producto));
        producto.setPrecio(-10.0);
        assertThrows(ValidationException.class, () -> productUseCase.crearProducto(producto));
    }

    @Test
    void crearProducto_StockNulo_ShouldThrowValidationException() {
        producto.setStock(null);
        assertThrows(ValidationException.class, () -> productUseCase.crearProducto(producto));
    }

    @Test
    void crearProducto_StockNegativo_ShouldThrowValidationException() {
        producto.setStock(-1);
        assertThrows(ValidationException.class, () -> productUseCase.crearProducto(producto));
    }

    @Test
    void obtenerProductoPorId_ExistingProduct_ShouldReturnProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(producto));

        Producto result = productUseCase.obtenerProductoPorId(1L);

        assertNotNull(result);
        assertEquals(producto.getId(), result.getId());
    }

    @Test
    void obtenerProductoPorId_NonExistingProduct_ShouldThrowProductoNotFoundException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductoNotFoundException.class, () -> productUseCase.obtenerProductoPorId(1L));
    }

    @Test
    void obtenerTodosLosProductos_ShouldReturnAllProducts() {
        List<Producto> productos = Arrays.asList(producto);
        when(productRepository.findAll()).thenReturn(productos);

        List<Producto> result = productUseCase.obtenerTodosLosProductos();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void actualizarProducto_ValidProduct_ShouldUpdateSuccessfully() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(productRepository.save(any(Producto.class))).thenReturn(producto);

        Producto result = productUseCase.actualizarProducto(1L, producto);

        assertNotNull(result);
        assertEquals(producto.getId(), result.getId());
        verify(productRepository).save(any(Producto.class));
    }

    @Test
    void actualizarProducto_NonExistingProduct_ShouldThrowProductoNotFoundException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductoNotFoundException.class, () -> productUseCase.actualizarProducto(1L, producto));
    }

    @Test
    void actualizarProducto_NullProduct_ShouldThrowValidationException() {
        assertThrows(ValidationException.class, () -> productUseCase.actualizarProducto(1L, null));
    }

    @Test
    void actualizarProducto_NombreNulo_ShouldThrowValidationException() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(producto));
        producto.setNombre(null);
        assertThrows(ValidationException.class, () -> productUseCase.actualizarProducto(1L, producto));
    }

    @Test
    void actualizarProducto_NombreVacio_ShouldThrowValidationException() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(producto));
        producto.setNombre("   ");
        assertThrows(ValidationException.class, () -> productUseCase.actualizarProducto(1L, producto));
    }

    @Test
    void actualizarProducto_PrecioNulo_ShouldThrowValidationException() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(producto));
        producto.setPrecio(null);
        assertThrows(ValidationException.class, () -> productUseCase.actualizarProducto(1L, producto));
    }

    @Test
    void actualizarProducto_PrecioMenorOIgualACero_ShouldThrowValidationException() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(producto));
        producto.setPrecio(0.0);
        assertThrows(ValidationException.class, () -> productUseCase.actualizarProducto(1L, producto));
        producto.setPrecio(-10.0);
        assertThrows(ValidationException.class, () -> productUseCase.actualizarProducto(1L, producto));
    }

    @Test
    void actualizarProducto_StockNulo_ShouldThrowValidationException() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(producto));
        producto.setStock(null);
        assertThrows(ValidationException.class, () -> productUseCase.actualizarProducto(1L, producto));
    }

    @Test
    void actualizarProducto_StockNegativo_ShouldThrowValidationException() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(producto));
        producto.setStock(-1);
        assertThrows(ValidationException.class, () -> productUseCase.actualizarProducto(1L, producto));
    }

    @Test
    void eliminarProductoPorId_ExistingProduct_ShouldDeleteSuccessfully() {
        when(productRepository.existsById(1L)).thenReturn(true);
        doNothing().when(productRepository).deleteById(1L);

        productUseCase.eliminarProductoPorId(1L);

        verify(productRepository).deleteById(1L);
    }

    @Test
    void eliminarProductoPorId_NonExistingProduct_ShouldThrowProductoNotFoundException() {
        when(productRepository.existsById(1L)).thenReturn(false);

        assertThrows(ProductoNotFoundException.class, () -> productUseCase.eliminarProductoPorId(1L));
    }

    @Test
    void eliminarProductoPorId_NullId_ShouldThrowValidationException() {
        assertThrows(ValidationException.class, () -> productUseCase.eliminarProductoPorId(null));
    }

    @Test
    void actualizarStockProducto_ValidUpdate_ShouldUpdateSuccessfully() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(productRepository.save(any(Producto.class))).thenReturn(producto);

        Producto result = productUseCase.actualizarStockProducto(1L, 5);

        assertNotNull(result);
        assertEquals(15, result.getStock()); // 10 (initial) + 5 (update)
        verify(productRepository).save(any(Producto.class));
    }

    @Test
    void actualizarStockProducto_NonExistingProduct_ShouldThrowProductoNotFoundException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductoNotFoundException.class, () -> productUseCase.actualizarStockProducto(1L, 5));
    }

    @Test
    void actualizarStockProducto_NullId_ShouldThrowValidationException() {
        assertThrows(ValidationException.class, () -> productUseCase.actualizarStockProducto(null, 5));
    }

    @Test
    void actualizarStockProducto_NullCantidad_ShouldThrowValidationException() {
        assertThrows(ValidationException.class, () -> productUseCase.actualizarStockProducto(1L, null));
    }

    @Test
    void actualizarStockProducto_NegativeResult_ShouldThrowStockUpdateException() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(producto));

        assertThrows(StockUpdateException.class, () -> productUseCase.actualizarStockProducto(1L, -20));
    }

    @Test
    void obtenerProductoPorId_NullId_ShouldThrowValidationException() {
        assertThrows(ValidationException.class, () -> productUseCase.obtenerProductoPorId(null));
    }

    @Test
    void obtenerProductoPorId_WithNullFields_ShouldReturnProduct() {
        producto.setNombre(null);
        producto.setDescripcion(null);
        producto.setPrecio(null);
        producto.setStock(null);
        when(productRepository.findById(1L)).thenReturn(Optional.of(producto));

        Producto result = productUseCase.obtenerProductoPorId(1L);

        assertNotNull(result);
        assertEquals(producto.getId(), result.getId());
        assertNull(result.getNombre());
        assertNull(result.getDescripcion());
        assertNull(result.getPrecio());
        assertNull(result.getStock());
    }
} 
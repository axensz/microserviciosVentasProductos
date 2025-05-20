package com.pragma.ventas.application.usecase;

import com.pragma.ventas.domain.model.Venta;
import com.pragma.ventas.domain.model.DetalleVenta;
import com.pragma.ventas.domain.model.Producto;
import com.pragma.ventas.domain.repository.VentaGateway;
import com.pragma.ventas.domain.repository.IProductoGateway;
import com.pragma.ventas.application.exception.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VentaUseCaseTest {
    @Mock
    private VentaGateway ventaGateway;
    @Mock
    private IProductoGateway productoGateway;
    @InjectMocks
    private VentaUseCase ventaUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarVenta_WhenVentaIsNull() {
        assertThrows(IllegalArgumentException.class, () -> ventaUseCase.registrarVenta(null));
        verify(ventaGateway, never()).guardarVenta(any());
    }

    @Test
    void testRegistrarVenta_WhenClienteIdIsNull() {
        Venta venta = Venta.builder().build();
        assertThrows(IllegalArgumentException.class, () -> ventaUseCase.registrarVenta(venta));
        verify(ventaGateway, never()).guardarVenta(any());
    }

    @Test
    void testRegistrarVenta_WhenClienteIdIsEmpty() {
        Venta venta = Venta.builder().clienteId("   ").build();
        assertThrows(IllegalArgumentException.class, () -> ventaUseCase.registrarVenta(venta));
        verify(ventaGateway, never()).guardarVenta(any());
    }

    @Test
    void testRegistrarVenta_WhenDetallesIsNull() {
        Venta venta = Venta.builder().clienteId("123").build();
        venta.setDetalles(null);
        assertThrows(IllegalArgumentException.class, () -> ventaUseCase.registrarVenta(venta));
        verify(ventaGateway, never()).guardarVenta(any());
    }

    @Test
    void testRegistrarVenta_WhenDetallesIsEmpty() {
        Venta venta = Venta.builder().clienteId("123").build();
        assertThrows(IllegalArgumentException.class, () -> ventaUseCase.registrarVenta(venta));
        verify(ventaGateway, never()).guardarVenta(any());
    }

    @Test
    void testRegistrarVenta_WhenProductoNoExiste() {
        Venta venta = Venta.builder()
            .clienteId("123")
            .build();
        DetalleVenta detalle = DetalleVenta.builder()
            .productoId(1L)
            .cantidad(1)
            .precioUnitario(10.0)
            .subtotal(10.0)
            .build();
        venta.agregarDetalle(detalle);
        when(productoGateway.validarExistenciaProducto(1L)).thenReturn(false);
        assertThrows(ProductNotFoundException.class, () -> ventaUseCase.registrarVenta(venta));
        verify(ventaGateway, never()).guardarVenta(any());
    }

    @Test
    void testRegistrarVenta_WhenStockInsuficiente() {
        Venta venta = Venta.builder()
            .clienteId("123")
            .build();
        DetalleVenta detalle = DetalleVenta.builder()
            .productoId(1L)
            .cantidad(20) // Más que el stock disponible
            .build();
        venta.agregarDetalle(detalle);
        
        Producto producto = Producto.builder()
            .id(1L)
            .nombre("Producto Test")
            .stock(10)
            .precio(10.0)
            .build();
            
        when(productoGateway.validarExistenciaProducto(1L)).thenReturn(true);
        when(productoGateway.obtenerProducto(1L)).thenReturn(producto);
        
        assertThrows(IllegalArgumentException.class, () -> ventaUseCase.registrarVenta(venta));
        verify(ventaGateway, never()).guardarVenta(any());
    }

    @Test
    void testRegistrarVenta_WhenProductoExiste() {
        Venta venta = Venta.builder()
            .clienteId("123")
            .build();
        DetalleVenta detalle = DetalleVenta.builder()
            .productoId(1L)
            .cantidad(1)
            .build();
        venta.agregarDetalle(detalle);
        
        Producto producto = Producto.builder()
            .id(1L)
            .nombre("Producto Test")
            .stock(10)
            .precio(10.0)
            .build();
            
        when(productoGateway.validarExistenciaProducto(1L)).thenReturn(true);
        when(productoGateway.obtenerProducto(1L)).thenReturn(producto);
        when(ventaGateway.guardarVenta(any(Venta.class))).thenReturn(venta);
        
        Venta result = ventaUseCase.registrarVenta(venta);
        
        assertNotNull(result);
        assertEquals(10.0, result.getTotal());
        assertEquals(10.0, detalle.getPrecioUnitario());
        assertEquals(10.0, detalle.getSubtotal());
        assertNotNull(result.getFecha());
        verify(ventaGateway).guardarVenta(venta);
    }

    @Test
    void testRegistrarVenta_WithMultipleDetalles() {
        Venta venta = Venta.builder()
            .clienteId("123")
            .build();
        
        // Primer detalle
        DetalleVenta detalle1 = DetalleVenta.builder()
            .productoId(1L)
            .cantidad(2)
            .build();
        venta.agregarDetalle(detalle1);
        
        // Segundo detalle
        DetalleVenta detalle2 = DetalleVenta.builder()
            .productoId(2L)
            .cantidad(3)
            .build();
        venta.agregarDetalle(detalle2);
        
        Producto producto1 = Producto.builder()
            .id(1L)
            .nombre("Producto 1")
            .stock(10)
            .precio(10.0)
            .build();
            
        Producto producto2 = Producto.builder()
            .id(2L)
            .nombre("Producto 2")
            .stock(10)
            .precio(20.0)
            .build();
            
        when(productoGateway.validarExistenciaProducto(1L)).thenReturn(true);
        when(productoGateway.validarExistenciaProducto(2L)).thenReturn(true);
        when(productoGateway.obtenerProducto(1L)).thenReturn(producto1);
        when(productoGateway.obtenerProducto(2L)).thenReturn(producto2);
        when(ventaGateway.guardarVenta(any(Venta.class))).thenReturn(venta);
        
        Venta result = ventaUseCase.registrarVenta(venta);
        
        assertNotNull(result);
        assertEquals(80.0, result.getTotal()); // (2 * 10.0) + (3 * 20.0)
        assertEquals(10.0, detalle1.getPrecioUnitario());
        assertEquals(20.0, detalle1.getSubtotal());
        assertEquals(20.0, detalle2.getPrecioUnitario());
        assertEquals(60.0, detalle2.getSubtotal());
        assertNotNull(result.getFecha());
        verify(ventaGateway).guardarVenta(venta);
    }
} 
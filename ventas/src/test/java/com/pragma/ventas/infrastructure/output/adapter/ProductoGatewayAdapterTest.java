package com.pragma.ventas.infrastructure.output.adapter;

import com.pragma.ventas.domain.model.Producto;
import com.pragma.ventas.infrastructure.output.client.ProductoClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductoGatewayAdapterTest {
    @Mock
    private ProductoClient productoClient;
    
    @InjectMocks
    private ProductoGatewayAdapter productoGatewayAdapter;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testObtenerProducto() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Test Product");
        
        ResponseEntity<Producto> response = ResponseEntity.ok(producto);
        when(productoClient.obtenerProducto(1L)).thenReturn(response);
        
        Producto result = productoGatewayAdapter.obtenerProducto(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Product", result.getNombre());
    }
    
    @Test
    void testObtenerProducto_WhenResponseIsNull() {
        when(productoClient.obtenerProducto(1L)).thenReturn(null);
        
        Producto result = productoGatewayAdapter.obtenerProducto(1L);
        assertNull(result);
    }
    
    @Test
    void testValidarExistenciaProducto() {
        ResponseEntity<Boolean> response = ResponseEntity.ok(true);
        when(productoClient.validarExistenciaProducto(1L)).thenReturn(response);
        
        boolean result = productoGatewayAdapter.validarExistenciaProducto(1L);
        assertTrue(result);
    }
    
    @Test
    void testValidarExistenciaProducto_WhenResponseIsNull() {
        when(productoClient.validarExistenciaProducto(1L)).thenReturn(null);
        
        boolean result = productoGatewayAdapter.validarExistenciaProducto(1L);
        assertFalse(result);
    }
    
    @Test
    void testValidarExistenciaProducto_WhenResponseBodyIsNull() {
        ResponseEntity<Boolean> response = ResponseEntity.ok(null);
        when(productoClient.validarExistenciaProducto(1L)).thenReturn(response);
        
        boolean result = productoGatewayAdapter.validarExistenciaProducto(1L);
        assertFalse(result);
    }
} 
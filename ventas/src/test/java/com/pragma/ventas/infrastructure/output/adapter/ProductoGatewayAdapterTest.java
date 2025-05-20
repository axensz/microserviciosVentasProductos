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
    void testObtenerProducto_WhenResponseIsNull() {
        when(productoClient.obtenerProducto(1L)).thenReturn(null);
        
        Producto result = productoGatewayAdapter.obtenerProducto(1L);
        
        assertNull(result);
        verify(productoClient).obtenerProducto(1L);
    }
    
    @Test
    void testObtenerProducto_WhenResponseBodyIsNull() {
        ResponseEntity<Producto> response = mock(ResponseEntity.class);
        when(response.getBody()).thenReturn(null);
        when(productoClient.obtenerProducto(1L)).thenReturn(response);
        
        Producto result = productoGatewayAdapter.obtenerProducto(1L);
        
        assertNull(result);
        verify(productoClient).obtenerProducto(1L);
    }
    
    @Test
    void testObtenerProducto_WhenResponseIsValid() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Test Product");
        
        ResponseEntity<Producto> response = mock(ResponseEntity.class);
        when(response.getBody()).thenReturn(producto);
        when(productoClient.obtenerProducto(1L)).thenReturn(response);
        
        Producto result = productoGatewayAdapter.obtenerProducto(1L);
        
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Product", result.getNombre());
        verify(productoClient).obtenerProducto(1L);
    }
    
    @Test
    void testValidarExistenciaProducto_WhenResponseIsNull() {
        when(productoClient.validarExistenciaProducto(1L)).thenReturn(null);
        
        boolean result = productoGatewayAdapter.validarExistenciaProducto(1L);
        
        assertFalse(result);
        verify(productoClient).validarExistenciaProducto(1L);
    }
    
    @Test
    void testValidarExistenciaProducto_WhenResponseBodyIsNull() {
        ResponseEntity<Boolean> response = mock(ResponseEntity.class);
        when(response.getBody()).thenReturn(null);
        when(productoClient.validarExistenciaProducto(1L)).thenReturn(response);
        
        boolean result = productoGatewayAdapter.validarExistenciaProducto(1L);
        
        assertFalse(result);
        verify(productoClient).validarExistenciaProducto(1L);
    }
    
    @Test
    void testValidarExistenciaProducto_WhenProductExists() {
        ResponseEntity<Boolean> response = mock(ResponseEntity.class);
        when(response.getBody()).thenReturn(true);
        when(productoClient.validarExistenciaProducto(1L)).thenReturn(response);
        
        boolean result = productoGatewayAdapter.validarExistenciaProducto(1L);
        
        assertTrue(result);
        verify(productoClient).validarExistenciaProducto(1L);
    }
    
    @Test
    void testValidarExistenciaProducto_WhenProductDoesNotExist() {
        ResponseEntity<Boolean> response = mock(ResponseEntity.class);
        when(response.getBody()).thenReturn(false);
        when(productoClient.validarExistenciaProducto(1L)).thenReturn(response);
        
        boolean result = productoGatewayAdapter.validarExistenciaProducto(1L);
        
        assertFalse(result);
        verify(productoClient).validarExistenciaProducto(1L);
    }
} 
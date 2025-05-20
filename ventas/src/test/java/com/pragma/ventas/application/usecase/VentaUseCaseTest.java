package com.pragma.ventas.application.usecase;

import com.pragma.ventas.domain.model.Venta;
import com.pragma.ventas.domain.model.DetalleVenta;
import com.pragma.ventas.domain.model.Producto;
import com.pragma.ventas.domain.repository.VentaGateway;
import com.pragma.ventas.domain.repository.IProductoGateway;
import com.pragma.ventas.application.exception.ProductNotFoundException;
import com.pragma.ventas.infrastructure.input.dto.VentaRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

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
        VentaRequestDto ventaDto = new VentaRequestDto();
        assertThrows(IllegalArgumentException.class, () -> ventaUseCase.registrarVenta(ventaDto));
        verify(ventaGateway, never()).guardarVenta(any());
    }

    @Test
    void testRegistrarVenta_WhenClienteIdIsEmpty() {
        VentaRequestDto ventaDto = new VentaRequestDto();
        ventaDto.setClienteId("   ");
        assertThrows(IllegalArgumentException.class, () -> ventaUseCase.registrarVenta(ventaDto));
        verify(ventaGateway, never()).guardarVenta(any());
    }

    @Test
    void testRegistrarVenta_WhenDetallesIsNull() {
        VentaRequestDto ventaDto = new VentaRequestDto();
        ventaDto.setClienteId("123");
        ventaDto.setDetalles(null);
        assertThrows(IllegalArgumentException.class, () -> ventaUseCase.registrarVenta(ventaDto));
        verify(ventaGateway, never()).guardarVenta(any());
    }

    @Test
    void testRegistrarVenta_WhenDetallesIsEmpty() {
        VentaRequestDto ventaDto = new VentaRequestDto();
        ventaDto.setClienteId("123");
        ventaDto.setDetalles(new ArrayList<>());
        assertThrows(IllegalArgumentException.class, () -> ventaUseCase.registrarVenta(ventaDto));
        verify(ventaGateway, never()).guardarVenta(any());
    }

    @Test
    void testRegistrarVenta_WhenProductoNoExiste() {
        VentaRequestDto ventaDto = new VentaRequestDto();
        ventaDto.setClienteId("123");
        
        VentaRequestDto.DetalleVentaDto detalleDto = VentaRequestDto.DetalleVentaDto.builder()
            .productoId(1L)
            .cantidad(1)
            .build();
        
        List<VentaRequestDto.DetalleVentaDto> detalles = new ArrayList<>();
        detalles.add(detalleDto);
        ventaDto.setDetalles(detalles);
        
        when(productoGateway.validarExistenciaProducto(1L)).thenReturn(false);
        assertThrows(ProductNotFoundException.class, () -> ventaUseCase.registrarVenta(ventaDto));
        verify(ventaGateway, never()).guardarVenta(any());
    }

    @Test
    void testRegistrarVenta_WhenStockInsuficiente() {
        VentaRequestDto ventaDto = new VentaRequestDto();
        ventaDto.setClienteId("123");
        
        VentaRequestDto.DetalleVentaDto detalleDto = VentaRequestDto.DetalleVentaDto.builder()
            .productoId(1L)
            .cantidad(20) // Más que el stock disponible
            .build();
        
        List<VentaRequestDto.DetalleVentaDto> detalles = new ArrayList<>();
        detalles.add(detalleDto);
        ventaDto.setDetalles(detalles);
        
        Producto producto = Producto.builder()
            .id(1L)
            .nombre("Producto Test")
            .stock(10)
            .precio(10.0)
            .build();
            
        when(productoGateway.validarExistenciaProducto(1L)).thenReturn(true);
        when(productoGateway.obtenerProducto(1L)).thenReturn(producto);
        
        assertThrows(IllegalArgumentException.class, () -> ventaUseCase.registrarVenta(ventaDto));
        verify(ventaGateway, never()).guardarVenta(any());
    }

    @Test
    void testRegistrarVenta_WhenProductoExiste() {
        VentaRequestDto ventaDto = new VentaRequestDto();
        ventaDto.setClienteId("123");
        
        VentaRequestDto.DetalleVentaDto detalleDto = VentaRequestDto.DetalleVentaDto.builder()
            .productoId(1L)
            .cantidad(1)
            .build();
        
        List<VentaRequestDto.DetalleVentaDto> detalles = new ArrayList<>();
        detalles.add(detalleDto);
        ventaDto.setDetalles(detalles);
        
        Producto producto = Producto.builder()
            .id(1L)
            .nombre("Producto Test")
            .stock(10)
            .precio(10.0)
            .build();
            
        when(productoGateway.validarExistenciaProducto(1L)).thenReturn(true);
        when(productoGateway.obtenerProducto(1L)).thenReturn(producto);
        
        Venta ventaEsperada = Venta.builder()
            .clienteId("123")
            .build();
        DetalleVenta detalleEsperado = DetalleVenta.builder()
            .productoId(1L)
            .cantidad(1)
            .precioUnitario(10.0)
            .subtotal(10.0)
            .build();
        ventaEsperada.agregarDetalle(detalleEsperado);
        
        when(ventaGateway.guardarVenta(any(Venta.class))).thenReturn(ventaEsperada);
        
        Venta result = ventaUseCase.registrarVenta(ventaDto);
        
        assertNotNull(result);
        assertEquals(10.0, result.getTotal());
        assertEquals(1, result.getDetalles().size());
        DetalleVenta detalleResult = result.getDetalles().get(0);
        assertEquals(10.0, detalleResult.getPrecioUnitario());
        assertEquals(10.0, detalleResult.getSubtotal());
        assertNotNull(result.getFecha());
        verify(ventaGateway).guardarVenta(any(Venta.class));
    }

    @Test
    void testRegistrarVenta_WithMultipleDetalles() {
        VentaRequestDto ventaDto = new VentaRequestDto();
        ventaDto.setClienteId("123");
        
        List<VentaRequestDto.DetalleVentaDto> detalles = new ArrayList<>();
        
        // Primer detalle
        VentaRequestDto.DetalleVentaDto detalle1Dto = VentaRequestDto.DetalleVentaDto.builder()
            .productoId(1L)
            .cantidad(2)
            .build();
        detalles.add(detalle1Dto);
        
        // Segundo detalle
        VentaRequestDto.DetalleVentaDto detalle2Dto = VentaRequestDto.DetalleVentaDto.builder()
            .productoId(2L)
            .cantidad(3)
            .build();
        detalles.add(detalle2Dto);
        
        ventaDto.setDetalles(detalles);
        
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
        
        Venta ventaEsperada = Venta.builder()
            .clienteId("123")
            .build();
        
        DetalleVenta detalle1Esperado = DetalleVenta.builder()
            .productoId(1L)
            .cantidad(2)
            .precioUnitario(10.0)
            .subtotal(20.0)
            .build();
            
        DetalleVenta detalle2Esperado = DetalleVenta.builder()
            .productoId(2L)
            .cantidad(3)
            .precioUnitario(20.0)
            .subtotal(60.0)
            .build();
            
        ventaEsperada.agregarDetalle(detalle1Esperado);
        ventaEsperada.agregarDetalle(detalle2Esperado);
        
        when(ventaGateway.guardarVenta(any(Venta.class))).thenReturn(ventaEsperada);
        
        Venta result = ventaUseCase.registrarVenta(ventaDto);
        
        assertNotNull(result);
        assertEquals(80.0, result.getTotal()); // (2 * 10.0) + (3 * 20.0)
        assertEquals(2, result.getDetalles().size());
        
        DetalleVenta detalle1Result = result.getDetalles().get(0);
        assertEquals(10.0, detalle1Result.getPrecioUnitario());
        assertEquals(20.0, detalle1Result.getSubtotal());
        
        DetalleVenta detalle2Result = result.getDetalles().get(1);
        assertEquals(20.0, detalle2Result.getPrecioUnitario());
        assertEquals(60.0, detalle2Result.getSubtotal());
        
        assertNotNull(result.getFecha());
        verify(ventaGateway).guardarVenta(any(Venta.class));
    }
} 
package com.pragma.ventas.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class VentaTest {
    @Test
    void testAgregarDetalleVenta() {
        Venta venta = Venta.builder().build();
        DetalleVenta detalle = DetalleVenta.builder().productoId(1L).cantidad(2).precioUnitario(100.0).subtotal(200.0).build();
        venta.agregarDetalle(detalle);
        assertEquals(1, venta.getDetalles().size());
        assertEquals(detalle, venta.getDetalles().get(0));
        assertEquals(venta, detalle.getVenta());
    }

    @Test
    void testRemoverDetalleVenta() {
        Venta venta = Venta.builder().build();
        DetalleVenta detalle = DetalleVenta.builder().productoId(1L).cantidad(2).precioUnitario(100.0).subtotal(200.0).build();
        venta.agregarDetalle(detalle);
        venta.removerDetalle(detalle);
        assertTrue(venta.getDetalles().isEmpty());
        assertNull(detalle.getVenta());
    }

    @Test
    void testDetallesInicializados() {
        Venta venta = Venta.builder().build();
        assertNotNull(venta.getDetalles());
        assertEquals(Collections.emptyList(), venta.getDetalles());
    }

    @Test
    void testAllArgsConstructor() {
        List<DetalleVenta> detalles = new ArrayList<>();
        LocalDateTime fecha = LocalDateTime.now();
        Venta venta = new Venta(1L, "CLI001", fecha, 150.0, detalles);
        
        assertEquals(1L, venta.getId());
        assertEquals("CLI001", venta.getClienteId());
        assertEquals(fecha, venta.getFecha());
        assertEquals(150.0, venta.getTotal());
        assertEquals(detalles, venta.getDetalles());
    }

    @Test
    void testNoArgsConstructor() {
        Venta venta = new Venta();
        assertNotNull(venta);
        assertNull(venta.getId());
        assertNull(venta.getClienteId());
        assertNull(venta.getFecha());
        assertNull(venta.getTotal());
        assertNotNull(venta.getDetalles());
        assertTrue(venta.getDetalles().isEmpty());
    }

    @Test
    void testBuilder() {
        LocalDateTime fecha = LocalDateTime.now();
        Venta venta = Venta.builder()
                .id(1L)
                .clienteId("CLI001")
                .fecha(fecha)
                .total(150.0)
                .build();
        
        assertEquals(1L, venta.getId());
        assertEquals("CLI001", venta.getClienteId());
        assertEquals(fecha, venta.getFecha());
        assertEquals(150.0, venta.getTotal());
        assertNotNull(venta.getDetalles());
        assertTrue(venta.getDetalles().isEmpty());
    }

    @Test
    void testSettersAndGetters() {
        Venta venta = new Venta();
        LocalDateTime fecha = LocalDateTime.now();
        List<DetalleVenta> detalles = new ArrayList<>();
        
        venta.setId(1L);
        assertEquals(1L, venta.getId());
        
        venta.setClienteId("CLI001");
        assertEquals("CLI001", venta.getClienteId());
        
        venta.setFecha(fecha);
        assertEquals(fecha, venta.getFecha());
        
        venta.setTotal(150.0);
        assertEquals(150.0, venta.getTotal());
        
        venta.setDetalles(detalles);
        assertEquals(detalles, venta.getDetalles());
    }

    @Test
    void testEqualsAndHashCode() {
        LocalDateTime fecha = LocalDateTime.now();
        Venta venta1 = new Venta(1L, "CLI001", fecha, 150.0, new ArrayList<>());
        Venta venta2 = new Venta(1L, "CLI001", fecha, 150.0, new ArrayList<>());
        Venta venta3 = new Venta(2L, "CLI002", fecha, 200.0, new ArrayList<>());
        
        // Test equals
        assertTrue(venta1.equals(venta2));
        assertTrue(venta2.equals(venta1));
        assertFalse(venta1.equals(venta3));
        assertFalse(venta1.equals(null));
        assertTrue(venta1.equals(venta1));
        
        // Test hashCode
        assertEquals(venta1.hashCode(), venta2.hashCode());
        assertNotEquals(venta1.hashCode(), venta3.hashCode());
    }

    @Test
    void testToString() {
        LocalDateTime fecha = LocalDateTime.now();
        Venta venta = new Venta(1L, "CLI001", fecha, 150.0, new ArrayList<>());
        String toString = venta.toString();
        
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("clienteId=CLI001"));
        assertTrue(toString.contains("fecha=" + fecha));
        assertTrue(toString.contains("total=150.0"));
    }

    @Test
    void testCanEqual() {
        LocalDateTime fecha = LocalDateTime.now();
        Venta venta1 = new Venta(1L, "CLI001", fecha, 150.0, new ArrayList<>());
        Venta venta2 = new Venta(1L, "CLI001", fecha, 150.0, new ArrayList<>());
        
        assertTrue(venta1.canEqual(venta2));
        assertTrue(venta2.canEqual(venta1));
    }
} 
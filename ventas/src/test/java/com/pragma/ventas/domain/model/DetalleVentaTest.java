package com.pragma.ventas.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Objects;

class DetalleVentaTest {
    @Test
    void testBuilderAndFields() {
        DetalleVenta detalle = DetalleVenta.builder()
                .productoId(1L)
                .cantidad(3)
                .precioUnitario(50.0)
                .subtotal(150.0)
                .build();
        assertEquals(1L, detalle.getProductoId());
        assertEquals(3, detalle.getCantidad());
        assertEquals(50.0, detalle.getPrecioUnitario());
        assertEquals(150.0, detalle.getSubtotal());
    }

    @Test
    void testSetVenta() {
        Venta venta = Venta.builder().build();
        DetalleVenta detalle = DetalleVenta.builder().build();
        detalle.setVenta(venta);
        assertEquals(venta, detalle.getVenta());
    }

    @Test
    void testAllArgsConstructor() {
        Venta venta = Venta.builder().build();
        DetalleVenta detalle = new DetalleVenta(1L, 2L, 3, 50.0, 150.0, venta);
        assertEquals(1L, detalle.getId());
        assertEquals(2L, detalle.getProductoId());
        assertEquals(3, detalle.getCantidad());
        assertEquals(50.0, detalle.getPrecioUnitario());
        assertEquals(150.0, detalle.getSubtotal());
        assertEquals(venta, detalle.getVenta());
    }

    @Test
    void testNoArgsConstructor() {
        DetalleVenta detalle = new DetalleVenta();
        assertNotNull(detalle);
        assertNull(detalle.getId());
        assertNull(detalle.getProductoId());
        assertNull(detalle.getCantidad());
        assertNull(detalle.getPrecioUnitario());
        assertNull(detalle.getSubtotal());
        assertNull(detalle.getVenta());
    }

    @Test
    void testSettersAndGetters() {
        DetalleVenta detalle = new DetalleVenta();
        
        detalle.setId(1L);
        assertEquals(1L, detalle.getId());
        
        detalle.setProductoId(2L);
        assertEquals(2L, detalle.getProductoId());
        
        detalle.setCantidad(3);
        assertEquals(3, detalle.getCantidad());
        
        detalle.setPrecioUnitario(50.0);
        assertEquals(50.0, detalle.getPrecioUnitario());
        
        detalle.setSubtotal(150.0);
        assertEquals(150.0, detalle.getSubtotal());
    }

    @Test
    void testEqualsAndHashCode() {
        // Caso 1: Objetos idénticos
        DetalleVenta detalle1 = new DetalleVenta(1L, 2L, 3, 50.0, 150.0, null);
        DetalleVenta detalle2 = new DetalleVenta(1L, 2L, 3, 50.0, 150.0, null);
        assertTrue(detalle1.equals(detalle2));
        assertEquals(detalle1.hashCode(), detalle2.hashCode());

        // Caso 2: Objetos diferentes
        DetalleVenta detalle3 = new DetalleVenta(2L, 3L, 4, 60.0, 240.0, null);
        assertFalse(detalle1.equals(detalle3));
        assertNotEquals(detalle1.hashCode(), detalle3.hashCode());

        // Caso 3: Comparación con null
        assertFalse(detalle1.equals(null));

        // Caso 4: Comparación con el mismo objeto
        assertTrue(detalle1.equals(detalle1));

        // Caso 5: Objetos con venta
        Venta venta1 = Venta.builder().id(1L).build();
        Venta venta2 = Venta.builder().id(1L).build();
        DetalleVenta detalle4 = new DetalleVenta(1L, 2L, 3, 50.0, 150.0, venta1);
        DetalleVenta detalle5 = new DetalleVenta(1L, 2L, 3, 50.0, 150.0, venta2);
        assertTrue(detalle4.equals(detalle5));
        assertEquals(detalle4.hashCode(), detalle5.hashCode());

        // Caso 6: Objetos con venta diferente
        Venta venta3 = Venta.builder().id(2L).build();
        DetalleVenta detalle6 = new DetalleVenta(1L, 2L, 3, 50.0, 150.0, venta3);
        assertFalse(detalle4.equals(detalle6));
        assertNotEquals(detalle4.hashCode(), detalle6.hashCode());

        // Caso 7: Objetos con campos nulos
        DetalleVenta detalle7 = new DetalleVenta();
        DetalleVenta detalle8 = new DetalleVenta();
        assertTrue(detalle7.equals(detalle8));
        assertEquals(detalle7.hashCode(), detalle8.hashCode());

        // Caso 8: Objeto con campos nulos vs objeto con valores
        assertFalse(detalle7.equals(detalle1));
        assertNotEquals(detalle7.hashCode(), detalle1.hashCode());

        // Caso 9: Diferentes tipos de objetos
        assertFalse(detalle1.equals(new Object()));
    }

    @Test
    void testToString() {
        DetalleVenta detalle = new DetalleVenta(1L, 2L, 3, 50.0, 150.0, null);
        String toString = detalle.toString();
        
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("productoId=2"));
        assertTrue(toString.contains("cantidad=3"));
        assertTrue(toString.contains("precioUnitario=50.0"));
        assertTrue(toString.contains("subtotal=150.0"));
    }

    @Test
    void testCanEqual() {
        DetalleVenta detalle1 = new DetalleVenta(1L, 2L, 3, 50.0, 150.0, null);
        DetalleVenta detalle2 = new DetalleVenta(1L, 2L, 3, 50.0, 150.0, null);
        assertTrue(detalle1.canEqual(detalle2));
        assertTrue(detalle2.canEqual(detalle1));
    }

    @Test
    void testBuilderToString() {
        DetalleVenta.DetalleVentaBuilder builder = DetalleVenta.builder();
        String builderStr = builder.toString();
        
        assertTrue(builderStr.contains("DetalleVentaBuilder"));
        assertTrue(builderStr.contains("id="));
        assertTrue(builderStr.contains("productoId="));
        assertTrue(builderStr.contains("cantidad="));
        assertTrue(builderStr.contains("precioUnitario="));
        assertTrue(builderStr.contains("subtotal="));
    }

    @Test
    void testBuilderId() {
        DetalleVenta.DetalleVentaBuilder builder = DetalleVenta.builder();
        DetalleVenta.DetalleVentaBuilder result = builder.id(1L);
        
        assertSame(builder, result);
        DetalleVenta detalle = builder.build();
        assertEquals(1L, detalle.getId());
    }

    @Test
    void testBuilderWithAllFields() {
        DetalleVenta detalle = DetalleVenta.builder()
                .id(1L)
                .productoId(2L)
                .cantidad(3)
                .precioUnitario(50.0)
                .subtotal(150.0)
                .build();

        assertEquals(1L, detalle.getId());
        assertEquals(2L, detalle.getProductoId());
        assertEquals(3, detalle.getCantidad());
        assertEquals(50.0, detalle.getPrecioUnitario());
        assertEquals(150.0, detalle.getSubtotal());
        assertNull(detalle.getVenta());
    }

    @Test
    void testBuilderWithNullFields() {
        DetalleVenta detalle = DetalleVenta.builder()
                .id(null)
                .productoId(null)
                .cantidad(null)
                .precioUnitario(null)
                .subtotal(null)
                .build();

        assertNull(detalle.getId());
        assertNull(detalle.getProductoId());
        assertNull(detalle.getCantidad());
        assertNull(detalle.getPrecioUnitario());
        assertNull(detalle.getSubtotal());
        assertNull(detalle.getVenta());
    }
} 
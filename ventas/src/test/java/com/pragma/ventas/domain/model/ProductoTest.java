package com.pragma.ventas.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {
    @Test
    void testNoArgsConstructor() {
        Producto producto = new Producto();
        assertNotNull(producto);
        assertNull(producto.getId());
        assertNull(producto.getNombre());
        assertNull(producto.getDescripcion());
        assertNull(producto.getPrecio());
        assertNull(producto.getStock());
    }

    @Test
    void testAllArgsConstructor() {
        Producto producto = new Producto(1L, "Nombre", "Desc", 10.0, 5);
        assertEquals(1L, producto.getId());
        assertEquals("Nombre", producto.getNombre());
        assertEquals("Desc", producto.getDescripcion());
        assertEquals(10.0, producto.getPrecio());
        assertEquals(5, producto.getStock());
    }

    @Test
    void testBuilder() {
        Producto producto = Producto.builder()
                .id(2L)
                .nombre("Test")
                .descripcion("DescTest")
                .precio(20.0)
                .stock(10)
                .build();
        assertEquals(2L, producto.getId());
        assertEquals("Test", producto.getNombre());
        assertEquals("DescTest", producto.getDescripcion());
        assertEquals(20.0, producto.getPrecio());
        assertEquals(10, producto.getStock());
    }

    @Test
    void testSettersAndGetters() {
        Producto producto = new Producto();
        producto.setId(3L);
        producto.setNombre("SetterTest");
        producto.setDescripcion("SetterDesc");
        producto.setPrecio(30.0);
        producto.setStock(15);
        assertEquals(3L, producto.getId());
        assertEquals("SetterTest", producto.getNombre());
        assertEquals("SetterDesc", producto.getDescripcion());
        assertEquals(30.0, producto.getPrecio());
        assertEquals(15, producto.getStock());
    }

    @Test
    void testEqualsAndHashCode() {
        // Caso 1: Objetos idénticos
        Producto p1 = new Producto(1L, "A", "B", 10.0, 5);
        Producto p2 = new Producto(1L, "A", "B", 10.0, 5);
        assertTrue(p1.equals(p2));
        assertEquals(p1.hashCode(), p2.hashCode());

        // Caso 2: Objetos diferentes
        Producto p3 = new Producto(2L, "C", "D", 20.0, 10);
        assertFalse(p1.equals(p3));
        assertNotEquals(p1.hashCode(), p3.hashCode());

        // Caso 3: Comparación con null
        assertFalse(p1.equals(null));

        // Caso 4: Comparación con el mismo objeto
        assertTrue(p1.equals(p1));

        // Caso 5: Objetos con campos nulos
        Producto p4 = new Producto();
        Producto p5 = new Producto();
        assertTrue(p4.equals(p5));
        assertEquals(p4.hashCode(), p5.hashCode());

        // Caso 6: Objeto con campos nulos vs objeto con valores
        assertFalse(p4.equals(p1));
        assertNotEquals(p4.hashCode(), p1.hashCode());

        // Caso 7: Diferentes tipos de objetos
        assertFalse(p1.equals("No soy un Producto"));

        // Caso 8: Diferentes valores en cada campo
        Producto p6 = new Producto(1L, "A", "B", 10.0, 5);
        Producto p7 = new Producto(1L, "A", "B", 10.0, 6); // stock diferente
        assertFalse(p6.equals(p7));
        assertNotEquals(p6.hashCode(), p7.hashCode());

        Producto p8 = new Producto(1L, "A", "B", 11.0, 5); // precio diferente
        assertFalse(p6.equals(p8));
        assertNotEquals(p6.hashCode(), p8.hashCode());

        Producto p9 = new Producto(1L, "A", "C", 10.0, 5); // descripción diferente
        assertFalse(p6.equals(p9));
        assertNotEquals(p6.hashCode(), p9.hashCode());

        Producto p10 = new Producto(1L, "B", "B", 10.0, 5); // nombre diferente
        assertFalse(p6.equals(p10));
        assertNotEquals(p6.hashCode(), p10.hashCode());

        Producto p11 = new Producto(2L, "A", "B", 10.0, 5); // id diferente
        assertFalse(p6.equals(p11));
        assertNotEquals(p6.hashCode(), p11.hashCode());

        // Caso 9: Campos nulos en diferentes posiciones
        Producto p12 = new Producto(null, "A", "B", 10.0, 5);
        Producto p13 = new Producto(1L, "A", "B", 10.0, 5);
        assertFalse(p12.equals(p13));
        assertNotEquals(p12.hashCode(), p13.hashCode());

        Producto p14 = new Producto(1L, null, "B", 10.0, 5);
        Producto p15 = new Producto(1L, "A", "B", 10.0, 5);
        assertFalse(p14.equals(p15));
        assertNotEquals(p14.hashCode(), p15.hashCode());

        Producto p16 = new Producto(1L, "A", null, 10.0, 5);
        Producto p17 = new Producto(1L, "A", "B", 10.0, 5);
        assertFalse(p16.equals(p17));
        assertNotEquals(p16.hashCode(), p17.hashCode());

        Producto p18 = new Producto(1L, "A", "B", null, 5);
        Producto p19 = new Producto(1L, "A", "B", 10.0, 5);
        assertFalse(p18.equals(p19));
        assertNotEquals(p18.hashCode(), p19.hashCode());

        Producto p20 = new Producto(1L, "A", "B", 10.0, null);
        Producto p21 = new Producto(1L, "A", "B", 10.0, 5);
        assertFalse(p20.equals(p21));
        assertNotEquals(p20.hashCode(), p21.hashCode());
    }

    @Test
    void testToString() {
        Producto producto = new Producto(1L, "Nombre", "Desc", 10.0, 5);
        String str = producto.toString();
        
        assertTrue(str.contains("id=1"));
        assertTrue(str.contains("nombre=Nombre"));
        assertTrue(str.contains("descripcion=Desc"));
        assertTrue(str.contains("precio=10.0"));
        assertTrue(str.contains("stock=5"));
    }

    @Test
    void testCanEqual() {
        Producto p1 = new Producto(1L, "A", "B", 10.0, 5);
        Producto p2 = new Producto(1L, "A", "B", 10.0, 5);
        assertTrue(p1.canEqual(p2));
        assertTrue(p2.canEqual(p1));
    }
} 
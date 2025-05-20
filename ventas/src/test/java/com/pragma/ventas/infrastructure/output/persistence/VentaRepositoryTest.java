package com.pragma.ventas.infrastructure.output.persistence;

import com.pragma.ventas.domain.model.Venta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class VentaRepositoryTest {
    @Autowired
    private VentaRepository ventaRepository;

    @Test
    void testGuardarYBuscarVenta() {
        Venta venta = Venta.builder()
                .clienteId("123")
                .fecha(LocalDateTime.now())
                .total(100.0)
                .build();
        Venta saved = ventaRepository.save(venta);
        assertNotNull(saved.getId());
        assertTrue(ventaRepository.findById(saved.getId()).isPresent());
    }
} 
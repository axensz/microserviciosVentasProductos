package com.pragma.ventas.infrastructure.output.adapter;

import com.pragma.ventas.domain.model.Venta;
import com.pragma.ventas.infrastructure.output.persistence.VentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class VentaGatewayAdapterTest {
    @Mock
    private VentaRepository ventaRepository;
    @InjectMocks
    private VentaGatewayAdapter ventaGatewayAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ventaGatewayAdapter = new VentaGatewayAdapter(ventaRepository);
    }

    @Test
    void testGuardarVenta() {
        Venta venta = Venta.builder().build();
        when(ventaRepository.save(venta)).thenReturn(venta);
        Venta result = ventaGatewayAdapter.guardarVenta(venta);
        assertNotNull(result);
        verify(ventaRepository).save(venta);
    }
} 
package com.pragma.ventas.infrastructure.input.controller;

import com.pragma.ventas.application.port.VentaInputPort;
import com.pragma.ventas.domain.model.Venta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.context.ActiveProfiles;

@WebMvcTest(VentaController.class)
@ActiveProfiles("test")
class VentaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VentaInputPort ventaInputPort;

    @Test
    void testRegistrarVenta() throws Exception {
        Venta venta = Venta.builder().build();
        when(ventaInputPort.registrarVenta(any(Venta.class))).thenReturn(venta);
        mockMvc.perform(post("/api/ventas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
        verify(ventaInputPort).registrarVenta(any(Venta.class));
    }
} 
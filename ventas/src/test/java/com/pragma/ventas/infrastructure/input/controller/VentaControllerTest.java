package com.pragma.ventas.infrastructure.input.controller;

import com.pragma.ventas.application.port.VentaInputPort;
import com.pragma.ventas.domain.model.Venta;
import com.pragma.ventas.infrastructure.input.dto.VentaRequestDto;
import com.pragma.ventas.infrastructure.input.dto.DetalleVentaRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(VentaController.class)
@ActiveProfiles("test")
@WithMockUser(roles = "VENDEDOR")
class VentaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @MockBean
    private VentaInputPort ventaInputPort;

    @Test
    void testRegistrarVenta() throws Exception {
        // Arrange
        VentaRequestDto ventaDto = new VentaRequestDto();
        ventaDto.setClienteId("123");
        
        DetalleVentaRequestDto detalleDto = new DetalleVentaRequestDto();
        detalleDto.setProductoId(1L);
        detalleDto.setCantidad(1);
        
        List<DetalleVentaRequestDto> detalles = new ArrayList<>();
        detalles.add(detalleDto);
        ventaDto.setDetalles(detalles);
        
        Venta ventaEsperada = Venta.builder()
            .clienteId("123")
            .build();
            
        when(ventaInputPort.registrarVenta(any(VentaRequestDto.class))).thenReturn(ventaEsperada);
        
        // Act & Assert
        mockMvc.perform(post("/api/v1/ventas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ventaDto)))
                .andExpect(status().isOk());
                
        verify(ventaInputPort).registrarVenta(any(VentaRequestDto.class));
    }
} 
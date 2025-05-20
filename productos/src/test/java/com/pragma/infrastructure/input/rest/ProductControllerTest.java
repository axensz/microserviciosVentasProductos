package com.pragma.infrastructure.input.rest;

import com.pragma.application.port.input.ProductInputPort;
import com.pragma.domain.model.Producto;
import com.pragma.infrastructure.input.rest.controller.ProductController;
import com.pragma.infrastructure.input.rest.dto.ProductRequestDto;
import com.pragma.infrastructure.input.rest.dto.ProductResponseDto;
import com.pragma.infrastructure.input.rest.mapper.ProductRestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductInputPort productInputPort;

    @MockBean
    private ProductRestMapper productRestMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private Producto producto;
    private ProductRequestDto requestDto;
    private ProductResponseDto responseDto;

    @BeforeEach
    void setUp() {
        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Test Product");
        producto.setDescripcion("Test Description");
        producto.setPrecio(100.0);
        producto.setStock(10);

        requestDto = new ProductRequestDto();
        requestDto.setNombre("Test Product");
        requestDto.setDescripcion("Test Description");
        requestDto.setPrecio(100.0);
        requestDto.setStock(10);

        responseDto = new ProductResponseDto();
        responseDto.setId(1L);
        responseDto.setNombre("Test Product");
        responseDto.setDescripcion("Test Description");
        responseDto.setPrecio(100.0);
        responseDto.setStock(10);
    }

    @Test
    void createProduct_ValidRequest_ShouldReturnCreated() throws Exception {
        when(productRestMapper.toProducto(any(ProductRequestDto.class))).thenReturn(producto);
        when(productInputPort.crearProducto(any(Producto.class))).thenReturn(producto);
        when(productRestMapper.toProductResponseDto(any(Producto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Test Product"));
    }

    @Test
    void getProduct_ExistingId_ShouldReturnProduct() throws Exception {
        when(productInputPort.obtenerProductoPorId(1L)).thenReturn(producto);
        when(productRestMapper.toProductResponseDto(any(Producto.class))).thenReturn(responseDto);

        mockMvc.perform(get("/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Test Product"));
    }

    @Test
    void getAllProducts_ShouldReturnList() throws Exception {
        List<Producto> productos = Arrays.asList(producto);
        List<ProductResponseDto> responseDtos = Arrays.asList(responseDto);

        when(productInputPort.obtenerTodosLosProductos()).thenReturn(productos);
        when(productRestMapper.toProductResponseDtoList(any())).thenReturn(responseDtos);

        mockMvc.perform(get("/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nombre").value("Test Product"));
    }

    @Test
    void updateProduct_ValidRequest_ShouldReturnUpdated() throws Exception {
        when(productRestMapper.toProducto(any(ProductRequestDto.class))).thenReturn(producto);
        when(productInputPort.actualizarProducto(any(Long.class), any(Producto.class))).thenReturn(producto);
        when(productRestMapper.toProductResponseDto(any(Producto.class))).thenReturn(responseDto);

        mockMvc.perform(put("/productos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Test Product"));
    }

    @Test
    void updateStock_ValidAmount_ShouldReturnUpdated() throws Exception {
        when(productInputPort.actualizarStockProducto(1L, 5)).thenReturn(producto);
        when(productRestMapper.toProductResponseDto(any(Producto.class))).thenReturn(responseDto);

        mockMvc.perform(patch("/productos/1/stock")
                .param("cantidad", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.stock").value(10));
    }

    @Test
    void deleteProduct_ExistingId_ShouldReturnNoContent() throws Exception {
        doNothing().when(productInputPort).eliminarProductoPorId(1L);

        mockMvc.perform(delete("/productos/1"))
                .andExpect(status().isNoContent());
    }
} 
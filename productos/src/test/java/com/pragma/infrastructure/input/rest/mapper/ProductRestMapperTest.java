package com.pragma.infrastructure.input.rest.mapper;

import com.pragma.domain.model.Producto;
import com.pragma.infrastructure.input.rest.dto.ProductRequestDto;
import com.pragma.infrastructure.input.rest.dto.ProductResponseDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRestMapperTest {

    private final ProductRestMapper mapper = Mappers.getMapper(ProductRestMapper.class);

    @Test
    void toProducto_ShouldMapRequestDtoToDomain() {
        // Arrange
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setNombre("Test Product");
        requestDto.setDescripcion("Test Description");
        requestDto.setPrecio(100.0);
        requestDto.setStock(10);

        // Act
        Producto producto = mapper.toProducto(requestDto);

        // Assert
        assertNotNull(producto);
        assertNull(producto.getId()); // ID should be null as it's ignored in the mapping
        assertEquals(requestDto.getNombre(), producto.getNombre());
        assertEquals(requestDto.getDescripcion(), producto.getDescripcion());
        assertEquals(requestDto.getPrecio(), producto.getPrecio());
        assertEquals(requestDto.getStock(), producto.getStock());
    }

    @Test
    void toProductResponseDto_ShouldMapDomainToResponseDto() {
        // Arrange
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Test Product");
        producto.setDescripcion("Test Description");
        producto.setPrecio(100.0);
        producto.setStock(10);

        // Act
        ProductResponseDto responseDto = mapper.toProductResponseDto(producto);

        // Assert
        assertNotNull(responseDto);
        assertEquals(producto.getId(), responseDto.getId());
        assertEquals(producto.getNombre(), responseDto.getNombre());
        assertEquals(producto.getDescripcion(), responseDto.getDescripcion());
        assertEquals(producto.getPrecio(), responseDto.getPrecio());
        assertEquals(producto.getStock(), responseDto.getStock());
    }

    @Test
    void toProductResponseDtoList_ShouldMapDomainListToResponseDtoList() {
        // Arrange
        Producto producto1 = new Producto();
        producto1.setId(1L);
        producto1.setNombre("Product 1");
        producto1.setDescripcion("Description 1");
        producto1.setPrecio(100.0);
        producto1.setStock(10);

        Producto producto2 = new Producto();
        producto2.setId(2L);
        producto2.setNombre("Product 2");
        producto2.setDescripcion("Description 2");
        producto2.setPrecio(200.0);
        producto2.setStock(20);

        List<Producto> productos = Arrays.asList(producto1, producto2);

        // Act
        List<ProductResponseDto> responseDtos = mapper.toProductResponseDtoList(productos);

        // Assert
        assertNotNull(responseDtos);
        assertEquals(2, responseDtos.size());
        
        ProductResponseDto dto1 = responseDtos.get(0);
        assertEquals(producto1.getId(), dto1.getId());
        assertEquals(producto1.getNombre(), dto1.getNombre());
        assertEquals(producto1.getDescripcion(), dto1.getDescripcion());
        assertEquals(producto1.getPrecio(), dto1.getPrecio());
        assertEquals(producto1.getStock(), dto1.getStock());

        ProductResponseDto dto2 = responseDtos.get(1);
        assertEquals(producto2.getId(), dto2.getId());
        assertEquals(producto2.getNombre(), dto2.getNombre());
        assertEquals(producto2.getDescripcion(), dto2.getDescripcion());
        assertEquals(producto2.getPrecio(), dto2.getPrecio());
        assertEquals(producto2.getStock(), dto2.getStock());
    }
} 
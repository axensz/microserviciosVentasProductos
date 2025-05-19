package com.pragma.infrastructure.output.feign.mapper;

import com.pragma.domain.model.Producto;
import com.pragma.infrastructure.output.feign.dto.ProductoResponseDto;
import org.mapstruct.Mapper;

/**
 * Interfaz para el mapeo entre DTOs del cliente Feign y entidades de dominio.
 * Utiliza MapStruct para generar la implementación automáticamente.
 */
@Mapper(componentModel = "spring")
public interface ProductoFeignMapper {

    /**
     * Convierte un DTO de respuesta de producto a una entidad de dominio.
     *
     * @param productoResponseDto El DTO de respuesta del microservicio de Productos.
     * @return La entidad de dominio Producto.
     */
    Producto toProducto(ProductoResponseDto productoResponseDto);
}
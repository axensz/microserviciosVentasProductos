package com.pragma.infrastructure.input.rest.mapper;

import com.pragma.domain.model.Producto;
import com.pragma.infrastructure.input.rest.dto.ProductRequestDto;
import com.pragma.infrastructure.input.rest.dto.ProductResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Mapper para la capa de infraestructura REST, encargado de convertir
 * entre DTOs (Data Transfer Objects) y el modelo de dominio {@link Producto}.
 * <p>
 * Utiliza MapStruct para la generación automática del código de mapeo.
 * El {@code componentModel = "spring"} permite que este mapper sea inyectable
 * como un bean de Spring.
 * {@code unmappedTargetPolicy = ReportingPolicy.IGNORE} evita que MapStruct
 * genere errores o advertencias si no todos los campos del destino son mapeados.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductRestMapper {

    /**
     * Convierte un {@link ProductRequestDto} a un objeto de dominio {@link Producto}.
     *
     * @param productRequestDto El DTO de solicitud de producto.
     * @return El objeto de dominio {@link Producto} mapeado.
     */
    @Mapping(target = "id", ignore = true)
    Producto toProducto(ProductRequestDto productRequestDto);

    /**
     * Convierte un objeto de dominio {@link Producto} a un {@link ProductResponseDto}.
     *
     * @param producto El objeto de dominio {@link Producto}.
     * @return El DTO de respuesta de producto {@link ProductResponseDto} mapeado.
     */
    ProductResponseDto toProductResponseDto(Producto producto);

    /**
     * Convierte una lista de objetos de dominio {@link Producto} a una lista de {@link ProductResponseDto}.
     *
     * @param productos La lista de objetos de dominio {@link Producto}.
     * @return Una lista de DTOs de respuesta de producto {@link ProductResponseDto}.
     */
    List<ProductResponseDto> toProductResponseDtoList(List<Producto> productos);
}

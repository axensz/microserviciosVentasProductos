package com.pragma.application.mapper;

import com.pragma.application.dto.ProductoResponseDto;
import com.pragma.domain.model.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ProductoClienteMapper {

    Producto toDomain(com.pragma.application.dto.ProductoResponseDto productoResponseDto); // Renamed from toProducto
    // Consider adding a method to map from domain.Producto to a DTO if needed for requests to product service
}

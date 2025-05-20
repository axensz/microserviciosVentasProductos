package com.pragma.ventas.infrastructure.input.dto;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class PageableRequest {
    @Parameter(description = "Número de página (0-based)")
    private int page = 0;

    @Parameter(description = "Tamaño de página")
    private int size = 10;

    @Parameter(description = "Campo por el cual ordenar")
    private String sortBy = "fecha";

    @Parameter(description = "Dirección del ordenamiento (asc/desc)")
    private String direction = "desc";
} 
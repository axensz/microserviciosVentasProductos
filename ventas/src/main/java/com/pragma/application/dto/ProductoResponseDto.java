package com.pragma.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponseDto {
    private Long id;
    private String nombre;
    private Double precio;
    private Integer stock;
}

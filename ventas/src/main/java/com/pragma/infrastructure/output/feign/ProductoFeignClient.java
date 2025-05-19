package com.pragma.infrastructure.output.feign;

import com.pragma.infrastructure.output.feign.dto.ProductoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Cliente Feign para comunicarse con el microservicio de Productos.
 * Define los métodos para interactuar con la API REST de Productos.
 */
@FeignClient(name = "productos-service", url = "${productos.service.url}")
public interface ProductoFeignClient {

    /**
     * Obtiene un producto por su ID.
     * Este método se utiliza para validar la existencia del producto y su stock disponible.
     *
     * @param id El ID del producto a obtener.
     * @return ResponseEntity con el producto encontrado.
     */
    @GetMapping("/api/v1/productos/{id}")
    ResponseEntity<ProductoResponseDto> obtenerProductoPorId(@PathVariable("id") Long id);

    /**
     * Actualiza el stock de un producto.
     * Este método se utiliza para decrementar el stock después de una venta.
     *
     * @param id El ID del producto cuyo stock se va a actualizar.
     * @param cantidad La cantidad a sumar o restar del stock (positiva para sumar, negativa para restar).
     * @return ResponseEntity con el producto actualizado.
     */
    @PatchMapping("/api/v1/productos/{id}/stock")
    ResponseEntity<ProductoResponseDto> actualizarStockProducto(@PathVariable("id") Long id, @RequestParam("cantidad") Integer cantidad);
}
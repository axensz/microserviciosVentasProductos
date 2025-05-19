package com.pragma.infrastructure.output.feign.client;

import com.pragma.application.dto.ProductoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "productos", url = "${feign.client.productos.url:http://productos:8080}")
public interface ProductoFeignClient {

    @GetMapping("/api/v1/productos/{id}")
    ResponseEntity<com.pragma.application.dto.ProductoResponseDto> obtenerProductoPorId(@PathVariable("id") Long id);

    @PatchMapping("/api/v1/productos/{id}/stock")
    ResponseEntity<com.pragma.application.dto.ProductoResponseDto> actualizarStockProducto(@PathVariable("id") Long id, @RequestParam("cantidad") int cantidad);
}

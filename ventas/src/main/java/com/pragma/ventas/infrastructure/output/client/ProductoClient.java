package com.pragma.ventas.infrastructure.output.client;

import com.pragma.ventas.domain.model.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productos", url = "${productos.service.url}")
public interface ProductoClient {
    
    @GetMapping("/api/v1/productos/{id}")
    ResponseEntity<Producto> obtenerProducto(@PathVariable("id") Long id);
    
    @GetMapping("/api/v1/productos/{id}/existe")
    ResponseEntity<Boolean> validarExistenciaProducto(@PathVariable("id") Long id);
} 
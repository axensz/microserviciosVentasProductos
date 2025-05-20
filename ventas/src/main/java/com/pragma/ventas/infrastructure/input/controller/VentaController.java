package com.pragma.ventas.infrastructure.input.controller;

import com.pragma.ventas.application.port.VentaInputPort;
import com.pragma.ventas.domain.model.Venta;
import com.pragma.ventas.infrastructure.input.dto.VentaRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/ventas")
@RequiredArgsConstructor
@Tag(name = "Ventas", description = "API para gestión de ventas")
public class VentaController {

    private final VentaInputPort ventaPort;

    @Operation(summary = "Registrar una nueva venta")
    @ApiResponse(responseCode = "200", description = "Venta registrada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de venta inválidos")
    @PostMapping
    @PreAuthorize("hasRole('VENDEDOR')")
    public ResponseEntity<Venta> registrarVenta(@Valid @RequestBody VentaRequestDto ventaDto) {
        return ResponseEntity.ok(ventaPort.registrarVenta(ventaDto));
    }

    @Operation(summary = "Obtener listado de ventas")
    @ApiResponse(responseCode = "200", description = "Listado de ventas obtenido exitosamente")
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Venta>> obtenerVentas(Pageable pageable) {
        return ResponseEntity.ok(ventaPort.obtenerVentas(pageable));
    }

    @Operation(summary = "Obtener una venta por ID")
    @ApiResponse(responseCode = "200", description = "Venta encontrada")
    @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    public ResponseEntity<Venta> obtenerVentaPorId(
            @Parameter(description = "ID de la venta") @PathVariable Long id) {
        return ResponseEntity.ok(ventaPort.obtenerVentaPorId(id));
    }

    @Operation(summary = "Obtener ventas por producto")
    @ApiResponse(responseCode = "200", description = "Listado de ventas por producto")
    @GetMapping("/producto/{productoId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Venta>> obtenerVentasPorProducto(
            @Parameter(description = "ID del producto") @PathVariable Long productoId,
            Pageable pageable) {
        return ResponseEntity.ok(ventaPort.obtenerVentasPorProducto(productoId, pageable));
    }

    @Operation(summary = "Obtener ventas por fecha")
    @ApiResponse(responseCode = "200", description = "Listado de ventas por fecha")
    @GetMapping("/fecha/{fecha}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Venta>> obtenerVentasPorFecha(
            @Parameter(description = "Fecha de las ventas") 
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            Pageable pageable) {
        return ResponseEntity.ok(ventaPort.obtenerVentasPorFecha(fecha, pageable));
    }
} 
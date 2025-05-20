package com.pragma.ventas.infrastructure.input.controller;

import com.pragma.ventas.application.port.VentaInputPort;
import com.pragma.ventas.domain.model.Venta;
import com.pragma.ventas.infrastructure.input.dto.PageableRequest;
import com.pragma.ventas.infrastructure.input.dto.VentaRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/ventas")
@RequiredArgsConstructor
@Tag(name = "Ventas", description = "API para gestión de ventas")
public class VentaController {

    private final VentaInputPort ventaInputPort;

    @PostMapping
    @Operation(summary = "Registrar una nueva venta")
    @ApiResponse(responseCode = "200", description = "Venta registrada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de venta inválidos")
    @PreAuthorize("hasRole('VENDEDOR')")
    @Transactional
    public ResponseEntity<Venta> registrarVenta(@Valid @RequestBody VentaRequestDto ventaRequestDto) {
        return ResponseEntity.ok(ventaInputPort.registrarVenta(ventaRequestDto));
    }

    @GetMapping
    @Operation(summary = "Obtener listado de ventas")
    @ApiResponse(responseCode = "200", description = "Listado de ventas obtenido exitosamente")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(readOnly = true)
    public ResponseEntity<Page<Venta>> obtenerVentas(@Valid PageableRequest pageableRequest) {
        PageRequest pageRequest = crearPageRequest(pageableRequest);
        return ResponseEntity.ok(ventaInputPort.obtenerVentas(pageRequest));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una venta por ID")
    @ApiResponse(responseCode = "200", description = "Venta encontrada")
    @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    @Transactional(readOnly = true)
    public ResponseEntity<Venta> obtenerVentaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ventaInputPort.obtenerVentaPorId(id));
    }

    @GetMapping("/producto/{productoId}")
    @Operation(summary = "Obtener ventas por producto")
    @ApiResponse(responseCode = "200", description = "Listado de ventas por producto")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(readOnly = true)
    public ResponseEntity<Page<Venta>> obtenerVentasPorProducto(
            @PathVariable Long productoId,
            @Valid PageableRequest pageableRequest) {
        PageRequest pageRequest = crearPageRequest(pageableRequest);
        return ResponseEntity.ok(ventaInputPort.obtenerVentasPorProducto(productoId, pageRequest));
    }

    @GetMapping("/fecha/{fecha}")
    @Operation(summary = "Obtener ventas por fecha")
    @ApiResponse(responseCode = "200", description = "Listado de ventas por fecha")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(readOnly = true)
    public ResponseEntity<Page<Venta>> obtenerVentasPorFecha(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @Valid PageableRequest pageableRequest) {
        PageRequest pageRequest = crearPageRequest(pageableRequest);
        return ResponseEntity.ok(ventaInputPort.obtenerVentasPorFecha(fecha, pageRequest));
    }

    private PageRequest crearPageRequest(PageableRequest pageableRequest) {
        Sort.Direction sortDirection = Sort.Direction.fromString(pageableRequest.getDirection().toUpperCase());
        return PageRequest.of(
            pageableRequest.getPage(),
            pageableRequest.getSize(),
            Sort.by(sortDirection, pageableRequest.getSortBy())
        );
    }
} 
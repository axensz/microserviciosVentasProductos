package com.pragma.ventas.infrastructure.input.controller;

import com.pragma.ventas.application.port.VentaInputPort;
import com.pragma.ventas.domain.model.Venta;
import com.pragma.ventas.infrastructure.input.dto.VentaRequestDto;
import com.pragma.ventas.infrastructure.input.dto.VentaResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST que expone los endpoints para la gestión de ventas.
 * Este controlador maneja las peticiones HTTP relacionadas con las operaciones CRUD
 * de ventas, transformando los DTOs de entrada y salida según sea necesario.
 *
 * @version 1.0
 * @since 2025-05-20
 */
@RestController
@RequestMapping("/api/v1/ventas")
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
    public ResponseEntity<VentaResponseDto> registrarVenta(@Valid @RequestBody VentaRequestDto ventaRequestDto) {
        Venta venta = ventaInputPort.registrarVenta(ventaRequestDto);
        return ResponseEntity.ok(convertToResponseDto(venta));
    }

    @GetMapping
    @Operation(summary = "Obtener listado de ventas")
    @ApiResponse(responseCode = "200", description = "Listado de ventas obtenido exitosamente")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(readOnly = true)
    public ResponseEntity<List<VentaResponseDto>> obtenerVentas() {
        List<Venta> ventas = ventaInputPort.obtenerVentas();
        return ResponseEntity.ok(ventas.stream()
            .map(this::convertToResponseDto)
            .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una venta por ID")
    @ApiResponse(responseCode = "200", description = "Venta encontrada exitosamente")
    @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    @Transactional(readOnly = true)
    public ResponseEntity<VentaResponseDto> obtenerVentaPorId(@PathVariable Long id) {
        Venta venta = ventaInputPort.obtenerVentaPorId(id);
        return ResponseEntity.ok(convertToResponseDto(venta));
    }

    @GetMapping("/producto/{productoId}")
    @Operation(summary = "Obtener ventas por producto")
    @ApiResponse(responseCode = "200", description = "Listado de ventas por producto")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(readOnly = true)
    public ResponseEntity<List<VentaResponseDto>> obtenerVentasPorProducto(@PathVariable Long productoId) {
        List<Venta> ventas = ventaInputPort.obtenerVentasPorProducto(productoId);
        return ResponseEntity.ok(ventas.stream()
            .map(this::convertToResponseDto)
            .collect(Collectors.toList()));
    }

    @GetMapping("/fecha/{fecha}")
    @Operation(summary = "Obtener ventas por fecha")
    @ApiResponse(responseCode = "200", description = "Listado de ventas por fecha")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(readOnly = true)
    public ResponseEntity<List<VentaResponseDto>> obtenerVentasPorFecha(@PathVariable String fecha) {
        LocalDate fechaLocal = LocalDate.parse(fecha);
        List<Venta> ventas = ventaInputPort.obtenerVentasPorFecha(fechaLocal);
        return ResponseEntity.ok(ventas.stream()
            .map(this::convertToResponseDto)
            .collect(Collectors.toList()));
    }

    /**
     * Convierte una entidad Venta a su correspondiente DTO de respuesta.
     *
     * @param venta Entidad Venta a convertir
     * @return VentaResponseDto con los datos de la venta
     */
    private VentaResponseDto convertToResponseDto(Venta venta) {
        return VentaResponseDto.builder()
            .id(venta.getId())
            .clienteId(venta.getClienteId())
            .fecha(venta.getFecha())
            .total(venta.getTotal())
            .detalles(venta.getDetalles().stream()
                .map(detalle -> VentaResponseDto.DetalleVentaDto.builder()
                    .productoId(detalle.getProductoId())
                    .cantidad(detalle.getCantidad())
                    .precioUnitario(detalle.getPrecioUnitario())
                    .subtotal(detalle.getSubtotal())
                    .build())
                .collect(Collectors.toList()))
            .build();
    }
} 
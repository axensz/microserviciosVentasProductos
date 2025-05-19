package com.pragma.infrastructure.input.rest.controller;

import com.pragma.application.port.input.VentaInputPort;
import com.pragma.domain.model.Venta;
import com.pragma.infrastructure.input.rest.dto.VentaRequestDto;
import com.pragma.infrastructure.input.rest.dto.VentaResponseDto;
import com.pragma.infrastructure.input.rest.mapper.VentaRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de ventas.
 * <p>
 * Proporciona endpoints para realizar operaciones relacionadas con ventas,
 * como registrar una venta y obtener el reporte de ventas.
 * Utiliza {@link VentaInputPort} para interactuar con la lógica de negocio
 * y {@link VentaRestMapper} para el mapeo entre DTOs y el modelo de dominio.
 */
@RestController
@RequestMapping("/api/v1/ventas")
@RequiredArgsConstructor
@Tag(name = "Ventas", description = "API para la gestión de ventas")
public class VentaController {

    private final VentaInputPort ventaInputPort;
    private final VentaRestMapper ventaRestMapper;

    /**
     * Registra una nueva venta.
     *
     * @param ventaRequestDto DTO con la información de la venta a registrar.
     * @return ResponseEntity con la venta registrada y estado HTTP 201 (Created).
     */
    @PostMapping
    @Operation(summary = "Registrar una nueva venta",
            description = "Registra una nueva venta en el sistema, validando el stock disponible y actualizando el stock de los productos.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Venta registrada exitosamente",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = VentaResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida o stock insuficiente")
            })
    public ResponseEntity<VentaResponseDto> registrarVenta(@Valid @RequestBody VentaRequestDto ventaRequestDto) {
        Venta venta = ventaRestMapper.toVenta(ventaRequestDto);
        Venta ventaRegistrada = ventaInputPort.registrarVenta(venta);
        return new ResponseEntity<>(ventaRestMapper.toVentaResponseDto(ventaRegistrada), HttpStatus.CREATED);
    }

    /**
     * Obtiene una venta por su ID.
     *
     * @param id El ID de la venta a obtener.
     * @return ResponseEntity con la venta encontrada y estado HTTP 200 (OK),
     * o estado HTTP 404 (Not Found) si la venta no existe.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una venta por ID",
            description = "Recupera los detalles de una venta específica utilizando su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Venta encontrada",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = VentaResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Venta no encontrada")
            })
    public ResponseEntity<VentaResponseDto> obtenerVentaPorId(@PathVariable Long id) {
        Venta venta = ventaInputPort.obtenerVentaPorId(id);
        return ResponseEntity.ok(ventaRestMapper.toVentaResponseDto(venta));
    }

    /**
     * Obtiene el reporte de todas las ventas.
     *
     * @return ResponseEntity con la lista de ventas y estado HTTP 200 (OK).
     */
    @GetMapping
    @Operation(summary = "Obtener reporte de ventas",
            description = "Recupera una lista de todas las ventas realizadas, incluyendo los productos vendidos, cantidades y stock restante.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Reporte de ventas recuperado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = VentaResponseDto.class)))
            })
    public ResponseEntity<List<VentaResponseDto>> obtenerReporteVentas() {
        List<Venta> ventas = ventaInputPort.obtenerTodasLasVentas();
        return ResponseEntity.ok(ventaRestMapper.toVentaResponseDtoList(ventas));
    }
}
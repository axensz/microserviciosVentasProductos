package com.pragma.ventas.infrastructure.input;

import com.pragma.ventas.application.port.VentaInputPort;
import com.pragma.ventas.domain.model.Venta;
import com.pragma.ventas.infrastructure.input.dto.VentaRequestDto;
import com.pragma.ventas.infrastructure.input.dto.VentaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaInputPort ventaUseCase;

    /**
     * Registra una nueva venta en el sistema.
     *
     * @param ventaDto DTO con la información de la venta a registrar
     * @return ResponseEntity con el DTO de la venta registrada
     */
    @PostMapping
    public ResponseEntity<VentaResponseDto> registrarVenta(@RequestBody VentaRequestDto ventaDto) {
        Venta venta = ventaUseCase.registrarVenta(ventaDto);
        return ResponseEntity.ok(convertToResponseDto(venta));
    }

    /**
     * Obtiene todas las ventas registradas en el sistema.
     *
     * @return ResponseEntity con la lista de DTOs de ventas
     */
    @GetMapping
    public ResponseEntity<List<VentaResponseDto>> obtenerVentas() {
        List<Venta> ventas = ventaUseCase.obtenerVentas();
        List<VentaResponseDto> ventasDto = ventas.stream()
            .map(this::convertToResponseDto)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ventasDto);
    }

    /**
     * Obtiene una venta específica por su ID.
     *
     * @param id ID de la venta a buscar
     * @return ResponseEntity con el DTO de la venta encontrada
     */
    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDto> obtenerVentaPorId(@PathVariable Long id) {
        Venta venta = ventaUseCase.obtenerVentaPorId(id);
        return ResponseEntity.ok(convertToResponseDto(venta));
    }

    /**
     * Obtiene todas las ventas que incluyen un producto específico.
     *
     * @param productoId ID del producto a buscar en las ventas
     * @return ResponseEntity con la lista de DTOs de ventas que incluyen el producto
     */
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<VentaResponseDto>> obtenerVentasPorProducto(@PathVariable Long productoId) {
        List<Venta> ventas = ventaUseCase.obtenerVentasPorProducto(productoId);
        List<VentaResponseDto> ventasDto = ventas.stream()
            .map(this::convertToResponseDto)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ventasDto);
    }

    /**
     * Obtiene todas las ventas realizadas en una fecha específica.
     *
     * @param fecha Fecha para filtrar las ventas
     * @return ResponseEntity con la lista de DTOs de ventas de la fecha especificada
     */
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<VentaResponseDto>> obtenerVentasPorFecha(@PathVariable LocalDate fecha) {
        List<Venta> ventas = ventaUseCase.obtenerVentasPorFecha(fecha);
        List<VentaResponseDto> ventasDto = ventas.stream()
            .map(this::convertToResponseDto)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ventasDto);
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
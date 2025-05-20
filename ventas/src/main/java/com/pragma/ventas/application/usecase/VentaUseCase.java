package com.pragma.ventas.application.usecase;

import com.pragma.ventas.application.exception.ProductNotFoundException;
import com.pragma.ventas.application.port.VentaInputPort;
import com.pragma.ventas.domain.model.DetalleVenta;
import com.pragma.ventas.domain.model.Venta;
import com.pragma.ventas.domain.repository.IProductoGateway;
import com.pragma.ventas.domain.repository.VentaGateway;
import com.pragma.ventas.infrastructure.input.dto.VentaRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * Implementation of {@link VentaInputPort} that defines the use cases for sales management.
 * This class contains the main business logic related to sales operations,
 * interacting with the persistence layer through repositories.
 */
@Service
@RequiredArgsConstructor
public class VentaUseCase implements VentaInputPort {

    private final IProductoGateway productoRepository;
    private final VentaGateway ventaGateway;

    /**
     * Registers a new sale in the system.
     *
     * @param ventaDto The {@link VentaRequestDto} object to register. Must not be null and must comply with basic validations.
     * @return The registered sale, usually with its ID assigned by the persistence system.
     * @throws IllegalArgumentException If the sale is null or its data is invalid.
     * @throws ProductNotFoundException If any of the products in the sale do not exist.
     */
    @Override
    @Transactional
    public Venta registrarVenta(VentaRequestDto ventaDto) {
        if (ventaDto == null) {
            throw new IllegalArgumentException("La venta no puede ser nula.");
        }
        if (ventaDto.getClienteId() == null || ventaDto.getClienteId().trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del cliente es requerido.");
        }
        if (ventaDto.getDetalles() == null || ventaDto.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("La venta debe tener al menos un detalle.");
        }

        Venta venta = new Venta();
        venta.setClienteId(ventaDto.getClienteId());

        // Validate product existence and calculate total
        double total = 0.0;
        for (var detalleDto : ventaDto.getDetalles()) {
            if (!productoRepository.validarExistenciaProducto(detalleDto.getProductoId())) {
                throw new ProductNotFoundException(
                    "Producto con ID " + detalleDto.getProductoId() + " no existe."
                );
            }
            
            // Get product to validate price and stock
            var producto = productoRepository.obtenerProducto(detalleDto.getProductoId());
            
            // Validate stock
            if (producto.getStock() < detalleDto.getCantidad()) {
                throw new IllegalArgumentException(
                    "Stock insuficiente para el producto " + producto.getNombre()
                );
            }
            
            // Create and set detail
            var detalle = DetalleVenta.builder()
                .productoId(detalleDto.getProductoId())
                .cantidad(detalleDto.getCantidad())
                .precioUnitario(producto.getPrecio())
                .subtotal(producto.getPrecio() * detalleDto.getCantidad())
                .build();
            
            venta.getDetalles().add(detalle);
            total += detalle.getSubtotal();
        }

        // Set date and total
        venta.setFecha(java.time.LocalDateTime.now());
        venta.setTotal(total);

        // Persist the sale
        return ventaGateway.guardarVenta(venta);
    }

    @Override
    public Page<Venta> obtenerVentas(Pageable pageable) {
        return ventaGateway.obtenerVentas(pageable);
    }

    @Override
    public Venta obtenerVentaPorId(Long id) {
        return ventaGateway.obtenerVentaPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada con ID: " + id));
    }

    @Override
    public Page<Venta> obtenerVentasPorProducto(Long productoId, Pageable pageable) {
        if (!productoRepository.validarExistenciaProducto(productoId)) {
            throw new ProductNotFoundException("Producto no encontrado con ID: " + productoId);
        }
        return ventaGateway.obtenerVentasPorProducto(productoId, pageable);
    }

    @Override
    public Page<Venta> obtenerVentasPorFecha(LocalDate fecha, Pageable pageable) {
        return ventaGateway.obtenerVentasPorFecha(fecha, pageable);
    }
} 
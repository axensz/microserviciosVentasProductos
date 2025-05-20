package com.pragma.ventas.application.usecase;

import com.pragma.ventas.application.exception.ProductNotFoundException;
import com.pragma.ventas.application.port.VentaInputPort;
import com.pragma.ventas.domain.model.Venta;
import com.pragma.ventas.domain.repository.IProductoGateway;
import com.pragma.ventas.domain.repository.VentaGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * @param venta The {@link Venta} object to register. Must not be null and must comply with basic validations.
     * @return The registered sale, usually with its ID assigned by the persistence system.
     * @throws IllegalArgumentException If the sale is null or its data is invalid.
     * @throws ProductNotFoundException If any of the products in the sale do not exist.
     */
    @Override
    @Transactional
    public Venta registrarVenta(Venta venta) {
        if (venta == null) {
            throw new IllegalArgumentException("Sale cannot be null.");
        }
        if (venta.getClienteId() == null || venta.getClienteId().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID is required.");
        }
        if (venta.getDetalles() == null || venta.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("Sale must have at least one detail.");
        }

        // Validate product existence and calculate total
        double total = 0.0;
        for (var detalle : venta.getDetalles()) {
            if (!productoRepository.validarExistenciaProducto(detalle.getProductoId())) {
                throw new ProductNotFoundException(
                    "Product with ID " + detalle.getProductoId() + " does not exist."
                );
            }
            
            // Get product to validate price and stock
            var producto = productoRepository.obtenerProducto(detalle.getProductoId());
            
            // Validate stock
            if (producto.getStock() < detalle.getCantidad()) {
                throw new IllegalArgumentException(
                    "Insufficient stock for product " + producto.getNombre()
                );
            }
            
            // Set unit price and calculate subtotal
            detalle.setPrecioUnitario(producto.getPrecio());
            detalle.setSubtotal(producto.getPrecio() * detalle.getCantidad());
            
            total += detalle.getSubtotal();
        }

        // Set date and total
        venta.setFecha(java.time.LocalDateTime.now());
        venta.setTotal(total);

        // Persist the sale
        return ventaGateway.guardarVenta(venta);
    }
} 
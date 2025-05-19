package com.pragma.application.usecase;

import com.pragma.application.exception.StockInsuficienteException;
import com.pragma.application.exception.ValidationException;
import com.pragma.application.exception.VentaNotFoundException;
import com.pragma.application.port.input.VentaInputPort;
import com.pragma.application.port.output.ProductoServicePort;
import com.pragma.domain.model.DetalleVenta;
import com.pragma.domain.model.Producto;
import com.pragma.domain.model.Venta;
import com.pragma.domain.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de {@link VentaInputPort} que define los casos de uso para la gestión de ventas.
 * Esta clase contiene la lógica de negocio principal relacionada con las operaciones de ventas,
 * interactuando con la capa de persistencia a través de {@link VentaRepository} y
 * con el microservicio de Productos a través de {@link ProductoServicePort}.
 */
@Service
@RequiredArgsConstructor
public class VentaUseCase implements VentaInputPort {

    private final VentaRepository ventaRepository;
    private final ProductoServicePort productoServicePort;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Venta registrarVenta(Venta venta) {
        validarVenta(venta);
        
        // Establecer la fecha actual si no se proporciona
        if (venta.getFecha() == null) {
            venta.setFecha(LocalDateTime.now());
        }
        
        // Procesar cada detalle de la venta
        List<DetalleVenta> detallesActualizados = new ArrayList<>();
        double totalVenta = 0.0;
        
        for (DetalleVenta detalle : venta.getDetalles()) {
            // Validar que el producto existe y tiene stock suficiente
            Producto producto = productoServicePort.obtenerProductoPorId(detalle.getProductoId());
            
            if (producto.getStock() < detalle.getCantidad()) {
                throw new StockInsuficienteException(
                    "Stock insuficiente para el producto con ID: " + producto.getId(),
                    producto.getId(),
                    producto.getStock(),
                    detalle.getCantidad()
                );
            }
            
            // Actualizar el stock del producto
            Producto productoActualizado = productoServicePort.actualizarStockProducto(
                producto.getId(),
                -detalle.getCantidad() // Restamos la cantidad vendida
            );
            
            // Actualizar el detalle con la información completa
            detalle.setNombreProducto(producto.getNombre());
            detalle.setPrecioUnitario(producto.getPrecio());
            detalle.setSubtotal(producto.getPrecio() * detalle.getCantidad());
            detalle.setStockRestante(productoActualizado.getStock());
            
            detallesActualizados.add(detalle);
            totalVenta += detalle.getSubtotal();
        }
        
        // Actualizar la venta con los detalles procesados y el total
        venta.setDetalles(detallesActualizados);
        venta.setTotal(totalVenta);
        
        // Persistir la venta
        return ventaRepository.save(venta);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Venta obtenerVentaPorId(Long id) {
        if (id == null) {
            throw new ValidationException("El ID de la venta no puede ser nulo.");
        }
        
        return ventaRepository.findById(id)
            .orElseThrow(() -> new VentaNotFoundException("Venta no encontrada con ID: " + id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Venta> obtenerTodasLasVentas() {
        return ventaRepository.findAll();
    }
    
    /**
     * Valida que los datos de la venta sean correctos.
     *
     * @param venta La venta a validar.
     * @throws ValidationException Si los datos de la venta no son válidos.
     */
    private void validarVenta(Venta venta) {
        if (venta == null) {
            throw new ValidationException("La venta no puede ser nula.");
        }
        
        if (venta.getDetalles() == null || venta.getDetalles().isEmpty()) {
            throw new ValidationException("La venta debe tener al menos un detalle.");
        }
        
        for (DetalleVenta detalle : venta.getDetalles()) {
            if (detalle.getProductoId() == null) {
                throw new ValidationException("El ID del producto en el detalle de venta no puede ser nulo.");
            }
            
            if (detalle.getCantidad() == null || detalle.getCantidad() <= 0) {
                throw new ValidationException("La cantidad en el detalle de venta debe ser mayor que cero.");
            }
        }
    }
}
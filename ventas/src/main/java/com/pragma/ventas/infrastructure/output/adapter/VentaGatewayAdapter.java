package com.pragma.ventas.infrastructure.output.adapter;

import com.pragma.ventas.domain.repository.VentaGateway;
import com.pragma.ventas.domain.model.Venta;
import com.pragma.ventas.infrastructure.output.persistence.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VentaGatewayAdapter implements VentaGateway {

    private final VentaRepository ventaRepository;

    @Override
    public Venta guardarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public Page<Venta> obtenerVentas(Pageable pageable) {
        return ventaRepository.findAll(pageable);
    }

    @Override
    public Optional<Venta> obtenerVentaPorId(Long id) {
        return ventaRepository.findById(id);
    }

    @Override
    public Page<Venta> obtenerVentasPorProducto(Long productoId, Pageable pageable) {
        return ventaRepository.findByDetallesProductoId(productoId, pageable);
    }

    @Override
    public Page<Venta> obtenerVentasPorFecha(LocalDate fecha, Pageable pageable) {
        LocalDateTime inicioDia = fecha.atStartOfDay();
        LocalDateTime finDia = fecha.plusDays(1).atStartOfDay();
        return ventaRepository.findByFechaBetween(inicioDia, finDia, pageable);
    }
} 
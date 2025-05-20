package com.pragma.ventas.infrastructure.output;

import com.pragma.ventas.domain.model.Venta;
import com.pragma.ventas.domain.repository.VentaGateway;
import com.pragma.ventas.infrastructure.output.jpa.VentaJpaRepository;
import com.pragma.ventas.infrastructure.output.jpa.mapper.VentaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del gateway de ventas que utiliza JPA para la persistencia.
 * Esta clase actúa como adaptador de salida en la arquitectura hexagonal,
 * transformando los modelos de dominio a entidades JPA y viceversa.
 *
 * @version 1.0
 * @since 2025-05-20
 */
@Repository
@RequiredArgsConstructor
public class VentaGatewayImpl implements VentaGateway {

    private final VentaJpaRepository ventaJpaRepository;
    private final VentaMapper ventaMapper;

    /**
     * {@inheritDoc}
     * <p>
     * Guarda una venta en la base de datos, transformando el modelo de dominio
     * a una entidad JPA antes de persistirla.
     * </p>
     */
    @Override
    public Venta guardarVenta(Venta venta) {
        var ventaEntity = ventaMapper.toEntity(venta);
        ventaEntity = ventaJpaRepository.save(ventaEntity);
        return ventaMapper.toDomain(ventaEntity);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Obtiene todas las ventas de la base de datos, transformando las entidades JPA
     * a modelos de dominio.
     * </p>
     */
    @Override
    public List<Venta> obtenerVentas() {
        return ventaJpaRepository.findAll().stream()
            .map(ventaMapper::toDomain)
            .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     * <p>
     * Busca una venta por su ID en la base de datos, transformando la entidad JPA
     * a modelo de dominio si se encuentra.
     * </p>
     */
    @Override
    public Optional<Venta> obtenerVentaPorId(Long id) {
        return ventaJpaRepository.findById(id)
            .map(ventaMapper::toDomain);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Obtiene todas las ventas que incluyen un producto específico, transformando
     * las entidades JPA a modelos de dominio.
     * </p>
     */
    @Override
    public List<Venta> obtenerVentasPorProducto(Long productoId) {
        return ventaJpaRepository.findByDetallesProductoId(productoId).stream()
            .map(ventaMapper::toDomain)
            .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     * <p>
     * Obtiene todas las ventas realizadas en una fecha específica, transformando
     * las entidades JPA a modelos de dominio.
     * </p>
     */
    @Override
    public List<Venta> obtenerVentasPorFecha(LocalDate fecha) {
        return ventaJpaRepository.findByFecha(fecha).stream()
            .map(ventaMapper::toDomain)
            .collect(Collectors.toList());
    }
} 
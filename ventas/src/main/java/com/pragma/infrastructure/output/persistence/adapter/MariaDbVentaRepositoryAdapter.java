package com.pragma.infrastructure.output.persistence.adapter;

import com.pragma.domain.model.Venta;
import com.pragma.domain.repository.VentaRepository;
import com.pragma.infrastructure.output.persistence.entity.DetalleVentaEntity;
import com.pragma.infrastructure.output.persistence.entity.VentaEntity;
import com.pragma.infrastructure.output.persistence.mapper.VentaPersistenceMapper;
import com.pragma.infrastructure.output.persistence.repository.VentaSpringDataJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Adaptador que implementa {@link VentaRepository} utilizando Spring Data JPA.
 * Esta clase actúa como un adaptador entre la capa de dominio y la capa de persistencia.
 */
@Component
@RequiredArgsConstructor
public class MariaDbVentaRepositoryAdapter implements VentaRepository {

    private final VentaSpringDataJpaRepository ventaSpringDataJpaRepository;
    private final VentaPersistenceMapper ventaPersistenceMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Venta> findById(Long id) {
        return ventaSpringDataJpaRepository.findById(id)
                .map(ventaPersistenceMapper::toVenta);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Venta> findAll() {
        return ventaPersistenceMapper.toVentaList(ventaSpringDataJpaRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Venta save(Venta venta) {
        VentaEntity ventaEntity = ventaPersistenceMapper.toVentaEntity(venta);
        
        // Limpiar la lista de detalles para evitar duplicados
        ventaEntity.getDetalles().clear();
        
        // Agregar cada detalle a la venta y establecer la relación bidireccional
        if (venta.getDetalles() != null) {
            venta.getDetalles().forEach(detalleVenta -> {
                DetalleVentaEntity detalleVentaEntity = ventaPersistenceMapper.toDetalleVentaEntity(detalleVenta);
                ventaEntity.addDetalle(detalleVentaEntity);
            });
        }
        
        // Guardar la venta y sus detalles
        VentaEntity savedVentaEntity = ventaSpringDataJpaRepository.save(ventaEntity);
        
        // Convertir la entidad guardada de vuelta al modelo de dominio
        return ventaPersistenceMapper.toVenta(savedVentaEntity);
    }
}
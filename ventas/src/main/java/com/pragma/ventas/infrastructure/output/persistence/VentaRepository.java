package com.pragma.ventas.infrastructure.output.persistence;

import com.pragma.ventas.domain.model.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    Page<Venta> findByDetallesProductoId(Long productoId, Pageable pageable);
    Page<Venta> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin, Pageable pageable);
} 
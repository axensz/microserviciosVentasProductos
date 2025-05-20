package com.pragma.ventas.infrastructure.output.persistence;

import com.pragma.ventas.domain.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByDetallesProductoId(Long productoId);
    List<Venta> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
} 
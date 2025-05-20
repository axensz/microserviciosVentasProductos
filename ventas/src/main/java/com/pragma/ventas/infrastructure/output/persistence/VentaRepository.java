package com.pragma.ventas.infrastructure.output.persistence;

import com.pragma.ventas.domain.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
} 
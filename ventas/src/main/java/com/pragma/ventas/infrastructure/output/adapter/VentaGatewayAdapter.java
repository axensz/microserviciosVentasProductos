package com.pragma.ventas.infrastructure.output.adapter;

import com.pragma.ventas.domain.repository.VentaGateway;
import com.pragma.ventas.domain.model.Venta;
import com.pragma.ventas.infrastructure.output.persistence.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VentaGatewayAdapter implements VentaGateway {

    private final VentaRepository ventaRepository;

    @Override
    public Venta guardarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }
} 
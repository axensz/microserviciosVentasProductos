package com.pragma.domain.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Representa una venta en el dominio de la aplicación.
 * Contiene información detallada sobre la venta, como su identificador,
 * fecha, productos vendidos y total.
 */
public class Venta {

    /**
     * Identificador único de la venta.
     */
    private Long id;

    /**
     * Fecha y hora en que se realizó la venta.
     */
    private LocalDateTime fecha;

    /**
     * Lista de detalles de la venta, que incluye los productos vendidos,
     * sus cantidades y precios.
     */
    private List<DetalleVenta> detalles;

    /**
     * Total de la venta.
     */
    private Double total;

    /**
     * Constructor por defecto.
     * Requerido por algunos frameworks de persistencia.
     */
    public Venta() {
    }

    /**
     * Constructor completo para crear una instancia de Venta.
     *
     * @param id El identificador único de la venta.
     * @param fecha La fecha y hora de la venta.
     * @param detalles La lista de detalles de la venta.
     * @param total El total de la venta.
     */
    public Venta(Long id, LocalDateTime fecha, List<DetalleVenta> detalles, Double total) {
        this.id = id;
        this.fecha = fecha;
        this.detalles = detalles;
        this.total = total;
    }

    /**
     * Obtiene el identificador único de la venta.
     *
     * @return El id de la venta.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la venta.
     *
     * @param id El nuevo id para la venta.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha y hora de la venta.
     *
     * @return La fecha de la venta.
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha y hora de la venta.
     *
     * @param fecha La nueva fecha para la venta.
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la lista de detalles de la venta.
     *
     * @return Los detalles de la venta.
     */
    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    /**
     * Establece la lista de detalles de la venta.
     *
     * @param detalles Los nuevos detalles para la venta.
     */
    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }

    /**
     * Obtiene el total de la venta.
     *
     * @return El total de la venta.
     */
    public Double getTotal() {
        return total;
    }

    /**
     * Establece el total de la venta.
     *
     * @param total El nuevo total para la venta.
     */
    public void setTotal(Double total) {
        this.total = total;
    }
}
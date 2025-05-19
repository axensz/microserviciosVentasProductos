package com.pragma.application.exception;

/**
 * Excepción que se lanza cuando se intenta realizar una venta de un producto
 * y no hay suficiente stock disponible.
 */
public class StockInsuficienteException extends RuntimeException {

    /**
     * Identificador del producto con stock insuficiente.
     */
    private final Long productoId;

    /**
     * Stock disponible del producto.
     */
    private final Integer stockDisponible;

    /**
     * Cantidad solicitada en la venta.
     */
    private final Integer cantidadSolicitada;

    /**
     * Constructor que recibe un mensaje de error, el ID del producto,
     * el stock disponible y la cantidad solicitada.
     *
     * @param message El mensaje que describe la razón de la excepción.
     * @param productoId El ID del producto con stock insuficiente.
     * @param stockDisponible El stock disponible del producto.
     * @param cantidadSolicitada La cantidad solicitada en la venta.
     */
    public StockInsuficienteException(String message, Long productoId, Integer stockDisponible, Integer cantidadSolicitada) {
        super(message);
        this.productoId = productoId;
        this.stockDisponible = stockDisponible;
        this.cantidadSolicitada = cantidadSolicitada;
    }

    /**
     * Obtiene el ID del producto con stock insuficiente.
     *
     * @return El ID del producto.
     */
    public Long getProductoId() {
        return productoId;
    }

    /**
     * Obtiene el stock disponible del producto.
     *
     * @return El stock disponible.
     */
    public Integer getStockDisponible() {
        return stockDisponible;
    }

    /**
     * Obtiene la cantidad solicitada en la venta.
     *
     * @return La cantidad solicitada.
     */
    public Integer getCantidadSolicitada() {
        return cantidadSolicitada;
    }
}
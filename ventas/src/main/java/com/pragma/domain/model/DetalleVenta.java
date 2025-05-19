package com.pragma.domain.model;

/**
 * Representa un detalle de venta en el dominio de la aplicación.
 * Contiene información sobre un producto específico en una venta,
 * como su identificador, producto, cantidad y precio.
 */
public class DetalleVenta {

    /**
     * Identificador único del detalle de venta.
     */
    private Long id;

    /**
     * Identificador del producto vendido.
     */
    private Long productoId;

    /**
     * Nombre del producto vendido.
     */
    private String nombreProducto;

    /**
     * Cantidad del producto vendido.
     */
    private Integer cantidad;

    /**
     * Precio unitario del producto al momento de la venta.
     */
    private Double precioUnitario;

    /**
     * Subtotal del detalle (cantidad * precioUnitario).
     */
    private Double subtotal;

    /**
     * Constructor por defecto.
     * Requerido por algunos frameworks de persistencia.
     */
    public DetalleVenta() {
    }

    /**
     * Constructor completo para crear una instancia de DetalleVenta.
     *
     * @param id El identificador único del detalle de venta.
     * @param productoId El identificador del producto vendido.
     * @param nombreProducto El nombre del producto vendido.
     * @param cantidad La cantidad del producto vendido.
     * @param precioUnitario El precio unitario del producto al momento de la venta.
     * @param subtotal El subtotal del detalle.
     */
    public DetalleVenta(Long id, Long productoId, String nombreProducto, Integer cantidad, Double precioUnitario, Double subtotal) {
        this.id = id;
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    /**
     * Obtiene el identificador único del detalle de venta.
     *
     * @return El id del detalle de venta.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del detalle de venta.
     *
     * @param id El nuevo id para el detalle de venta.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el identificador del producto vendido.
     *
     * @return El id del producto.
     */
    public Long getProductoId() {
        return productoId;
    }

    /**
     * Establece el identificador del producto vendido.
     *
     * @param productoId El nuevo id del producto.
     */
    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    /**
     * Obtiene el nombre del producto vendido.
     *
     * @return El nombre del producto.
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * Establece el nombre del producto vendido.
     *
     * @param nombreProducto El nuevo nombre del producto.
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * Obtiene la cantidad del producto vendido.
     *
     * @return La cantidad vendida.
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del producto vendido.
     *
     * @param cantidad La nueva cantidad vendida.
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el precio unitario del producto al momento de la venta.
     *
     * @return El precio unitario.
     */
    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Establece el precio unitario del producto al momento de la venta.
     *
     * @param precioUnitario El nuevo precio unitario.
     */
    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * Obtiene el subtotal del detalle.
     *
     * @return El subtotal.
     */
    public Double getSubtotal() {
        return subtotal;
    }

    /**
     * Establece el subtotal del detalle.
     *
     * @param subtotal El nuevo subtotal.
     */
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
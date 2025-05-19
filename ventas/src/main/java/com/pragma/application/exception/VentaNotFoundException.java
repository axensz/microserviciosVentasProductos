package com.pragma.application.exception;

/**
 * Excepción que se lanza cuando se intenta acceder a una venta que no existe.
 */
public class VentaNotFoundException extends RuntimeException {

    /**
     * Constructor que recibe un mensaje de error.
     *
     * @param message El mensaje que describe la razón de la excepción.
     */
    public VentaNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor que recibe un mensaje de error y la causa original de la excepción.
     *
     * @param message El mensaje que describe la razón de la excepción.
     * @param cause La causa original de la excepción.
     */
    public VentaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
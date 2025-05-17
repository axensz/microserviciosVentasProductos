package com.pragma.application.exception;

/**
 * Excepción lanzada cuando no se encuentra un producto específico en el sistema.
 */
public class ProductoNotFoundException extends RuntimeException {

    /**
     * Constructor que toma un mensaje detallado sobre la excepción.
     *
     * @param message El mensaje que describe la razón de la excepción.
     */
    public ProductoNotFoundException(String message) {
        super(message);
    }
}

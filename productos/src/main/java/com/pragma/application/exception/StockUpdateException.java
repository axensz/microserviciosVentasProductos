package com.pragma.application.exception;

/**
 * Excepción lanzada cuando ocurre un error durante la actualización del stock
 * de un producto, por ejemplo, intentar establecer un stock negativo.
 */
public class StockUpdateException extends RuntimeException {

    /**
     * Constructor que toma un mensaje detallado sobre la excepción.
     *
     * @param message El mensaje que describe la razón de la excepción.
     */
    public StockUpdateException(String message) {
        super(message);
    }
}

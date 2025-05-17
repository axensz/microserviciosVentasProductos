package com.pragma.application.exception;

/**
 * Excepción lanzada cuando ocurre un error de validación de datos
 * en la capa de aplicación.
 */
public class ValidationException extends RuntimeException {

    /**
     * Constructor que toma un mensaje detallado sobre la excepción.
     *
     * @param message El mensaje que describe la razón de la excepción.
     */
    public ValidationException(String message) {
        super(message);
    }
}

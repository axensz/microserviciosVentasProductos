package com.pragma.application.exception;

/**
 * Excepción que se lanza cuando los datos de entrada no cumplen con las validaciones requeridas.
 * Por ejemplo, cuando un campo obligatorio está vacío o un valor numérico está fuera de rango.
 */
public class ValidationException extends RuntimeException {

    /**
     * Constructor que recibe un mensaje de error.
     *
     * @param message El mensaje que describe la razón de la excepción.
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * Constructor que recibe un mensaje de error y la causa original de la excepción.
     *
     * @param message El mensaje que describe la razón de la excepción.
     * @param cause La causa original de la excepción.
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
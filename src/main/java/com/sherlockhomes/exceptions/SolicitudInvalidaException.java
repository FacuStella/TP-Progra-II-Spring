package com.sherlockhomes.exceptions;

/** Datos de entrada inválidos que no cubre Bean Validation (HTTP 400). */
public class SolicitudInvalidaException extends RuntimeException {
    public SolicitudInvalidaException(String mensaje) {
        super(mensaje);
    }
}

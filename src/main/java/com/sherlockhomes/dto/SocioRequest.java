package com.sherlockhomes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * username y password son obligatorios al registrar. Al actualizar, el username se ignora
 * y el password solo se cambia si viene informado.
 */
public record SocioRequest(
        @NotBlank String nombre,
        @NotNull Integer dni,
        String direccion,
        String telefono,
        String username,
        String password) {
}

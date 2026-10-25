package com.sherlockhomes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VehiculoRequest(
        @NotBlank String patente,
        @NotBlank String marca,
        @NotBlank String tipo,
        @NotBlank String dimensiones,
        @NotNull Integer dniPropietario) {
}

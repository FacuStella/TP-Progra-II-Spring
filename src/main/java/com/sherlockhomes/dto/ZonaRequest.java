package com.sherlockhomes.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ZonaRequest(
        @NotBlank String letra,
        @NotBlank String tipoVehiculos,
        @NotNull @Min(1) Integer profundidadGarage,
        @NotNull @Min(1) Integer anchoGarage) {
}

package com.empresaservicios.soporte.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record TecnicoCreateDTO(
        @Valid
        @NotNull(message = "Datos personales del técnico son obligatorios")
        DatosPersonaDTO datosPersona
) {
}

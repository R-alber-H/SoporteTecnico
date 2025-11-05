package com.empresaservicios.soporte.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosPersonaUpdateDTO(
        @NotBlank(message = "Los nombres no pueden estar vacíos")
        String nombres,

        @NotBlank(message = "Los apellidos no pueden estar vacíos")
        String apellidos,

        String telefono
) {
}

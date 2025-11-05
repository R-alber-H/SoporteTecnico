package com.empresaservicios.soporte.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DatosPersonaDTO(
        @NotBlank(message = "Los nombres no pueden estar vacíos")
        String nombres,

        @NotBlank(message = "Los apellidos no pueden estar vacíos")
        String apellidos,

        @NotBlank(message = "El DNI no puede estar vacío")
        @Size(min = 8, max = 8, message = "El DNI debe tener 8 caracteres")
        String dni,

        String telefono
) {
}

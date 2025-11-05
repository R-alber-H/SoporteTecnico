package com.empresaservicios.soporte.dto;

import jakarta.validation.constraints.NotBlank;

public record SolicitudUpdateDTO(
        @NotBlank(message = "El asunto no puede estar vacío")
        String asunto,

        @NotBlank(message = "La descripción no puede estar vacía")
        String descripcion
) {
}

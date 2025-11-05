package com.empresaservicios.soporte.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SolicitudCreateDTO(
        @NotBlank(message = "El asunto es obligatorio")
        String asunto,

        @NotBlank(message = "La descripción es obligatoria")
        String descripcion,

        @NotNull(message = "El id del cliente es obligatorio")
        Long clienteId) {
}

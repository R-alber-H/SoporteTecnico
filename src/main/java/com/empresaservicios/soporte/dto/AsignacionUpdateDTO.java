package com.empresaservicios.soporte.dto;

import jakarta.validation.constraints.NotNull;

public record AsignacionUpdateDTO(
        @NotNull(message = "El id del técnico es obligatorio")
        Long tecnicoId,

        @NotNull(message = "El id de la solicitud es obligatorio")
        Long solicitudId
) {
}

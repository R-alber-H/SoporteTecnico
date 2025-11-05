package com.empresaservicios.soporte.dto;

public record AsignacionDTO(
        Long id,
        Long tecnicoId,
        String tecnicoNombre,
        Long solicitudId,
        String solicitudAsunto
) {
}

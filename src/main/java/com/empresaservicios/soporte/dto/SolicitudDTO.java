package com.empresaservicios.soporte.dto;

import com.empresaservicios.soporte.utils.enums.Activo;
import com.empresaservicios.soporte.utils.enums.Estado;

public record SolicitudDTO(
        Long id,
        String asunto,
        String descripcion,
        Estado estado,
        Activo activo,
        Long clienteId,
        String motivoRechazo){
}

package com.empresaservicios.soporte.dto;

import com.empresaservicios.soporte.utils.enums.Activo;

public record TecnicoDTO(
        Long id,
        Activo activo,
        DatosPersonaDTO datosPersona
) {
}

package com.empresaservicios.soporte.dto;

import com.empresaservicios.soporte.utils.enums.Activo;
import com.empresaservicios.soporte.utils.enums.TipoCliente;

public record ClienteDTO(
        Long id,
        TipoCliente tipoCliente,
        Activo activo,

        DatosPersonaDTO datosPersona,
        DatosEmpresaDTO datosEmpresa
) {
}

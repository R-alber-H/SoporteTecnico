package com.empresaservicios.soporte.dto;

import com.empresaservicios.soporte.utils.enums.TipoCliente;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record ClienteCreateDTO(
        @NotNull(message = "El tipo de cliente es obligatorio")
        TipoCliente tipoCliente,

        @Valid
        DatosPersonaDTO datosPersona, // opcional, solo si tipoCliente == PERSONA

        @Valid
        DatosEmpresaDTO datosEmpresa  // opcional, solo si tipoCliente == EMPRESA
) {
}

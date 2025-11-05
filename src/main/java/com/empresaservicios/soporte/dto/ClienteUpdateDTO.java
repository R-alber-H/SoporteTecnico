package com.empresaservicios.soporte.dto;

import com.empresaservicios.soporte.utils.enums.TipoCliente;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record ClienteUpdateDTO(
        @NotNull(message = "El tipo de cliente es obligatorio")
        TipoCliente tipoCliente,

        @Valid
        DatosPersonaUpdateDTO datosPersona,

        @Valid
        DatosEmpresaUpdateDTO datosEmpresa
) {
}

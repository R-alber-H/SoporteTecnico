package com.empresaservicios.soporte.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosEmpresaUpdateDTO(
        @NotBlank(message = "La razón social no puede estar vacía")
        String razonSocial,

        String telefono
) {
}

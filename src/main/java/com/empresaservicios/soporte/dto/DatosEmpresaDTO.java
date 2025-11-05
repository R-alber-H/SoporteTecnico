package com.empresaservicios.soporte.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DatosEmpresaDTO(
        @NotBlank(message = "La razón social no puede estar vacía")
        String razonSocial,

        @NotBlank(message = "El RUC no puede estar vacío")
        @Size(max = 11)
        String ruc,

        String telefono
) {
}

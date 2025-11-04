package com.empresaservicios.soporte.dto;

import jakarta.validation.constraints.Size;

public record TecnicoUpdateDTO(
        @Size(max = 100, message = "El nombre no puede tener mas de 100 caracteres")
        String nombres,

        @Size(max = 100, message = "El apellido no puede tener mas de 100 caracteres")
        String apellidos,

        @Size(max = 20, message = "El telefono no puede tener mas de 20 caracteres")
        String telefono
) {
}

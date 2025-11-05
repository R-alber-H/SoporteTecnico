package com.empresaservicios.soporte.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatosPersona extends BaseEntity{
    
    @Column(length = 100,nullable = false)
    @NotBlank(message = "Los nombres no puede estar vacio")
    private String nombres;

    @Column(length = 100,nullable = false)
    @NotBlank(message = "Los apellidos no puede estar vacio")
    private String apellidos;

    @Column(length = 8,nullable = false,unique = true)
    @NotBlank(message = "El DNI no puede estar vacio")
    @Size(min = 8, max = 8, message = "El DNI debe tener 8 caracteres")
    private String dni;

    private String telefono;
}

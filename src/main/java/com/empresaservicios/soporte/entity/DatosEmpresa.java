package com.empresaservicios.soporte.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.NotBlank;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class DatosEmpresa extends BaseEntity{

    @Column(nullable = false)
    @NotBlank(message = "La razon social no puede estar vacia")
    private String razonSocial;

    @Column(length = 11,nullable = false,unique = true)
    @NotBlank(message = "La ruc no puede estar vacia")
    private String ruc;

    private String telefono;
 
}

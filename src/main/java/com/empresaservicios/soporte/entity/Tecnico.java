package com.empresaservicios.soporte.entity;

import com.empresaservicios.soporte.utils.enums.Activo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Tecnico extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Activo activo = Activo.SI;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_datos_persona", nullable = false)
    private DatosPersona datosPersona;

}

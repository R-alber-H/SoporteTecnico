package com.empresaservicios.soporte.entity;

import com.empresaservicios.soporte.utils.enums.Activo;
import com.empresaservicios.soporte.utils.enums.TipoCliente;

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
public class Cliente extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCliente tipoCliente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Activo activo = Activo.Si;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_datos_persona")
    private DatosPersona datosPersona;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_datos_empresa")
    private DatosEmpresa datosEmpresa;

}

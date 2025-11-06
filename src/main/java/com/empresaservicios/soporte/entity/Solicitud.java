package com.empresaservicios.soporte.entity;

import com.empresaservicios.soporte.utils.enums.Activo;
import com.empresaservicios.soporte.utils.enums.Estado;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Solicitud extends BaseEntity  {

    @NotBlank
    private String asunto;

    @NotBlank
    private String descripcion;
    private String motivoRechazo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Activo activo ;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado ;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
}

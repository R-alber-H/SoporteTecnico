package com.empresaservicios.soporte.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Asignacion extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_tecnico", nullable = false)
    private Tecnico tecnico;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_solicitud", nullable = false)
    private Solicitud solicitud;
    
}

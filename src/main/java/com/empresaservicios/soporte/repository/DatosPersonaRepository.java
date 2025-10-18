package com.empresaservicios.soporte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresaservicios.soporte.entity.DatosPersona;

public interface DatosPersonaRepository extends JpaRepository<DatosPersona,Long> {
    
}

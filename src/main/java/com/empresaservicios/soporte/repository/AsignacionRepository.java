package com.empresaservicios.soporte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresaservicios.soporte.entity.Asignacion;

public interface AsignacionRepository extends JpaRepository<Asignacion,Long> {
    
}

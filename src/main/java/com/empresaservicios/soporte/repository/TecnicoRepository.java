package com.empresaservicios.soporte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresaservicios.soporte.entity.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico,Long>{
    
}

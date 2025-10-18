package com.empresaservicios.soporte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresaservicios.soporte.entity.DatosEmpresa;

public interface DatosEmpresaRepository extends JpaRepository<DatosEmpresa,Long> {
    
}

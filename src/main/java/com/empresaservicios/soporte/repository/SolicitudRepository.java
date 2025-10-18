package com.empresaservicios.soporte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresaservicios.soporte.entity.Solicitud;

public interface SolicitudRepository extends JpaRepository<Solicitud ,Long>{
    
}

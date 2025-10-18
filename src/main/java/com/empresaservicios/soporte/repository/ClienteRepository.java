package com.empresaservicios.soporte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresaservicios.soporte.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    
}

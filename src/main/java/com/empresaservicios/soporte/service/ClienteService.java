package com.empresaservicios.soporte.service;

import com.empresaservicios.soporte.entity.Cliente;

public interface ClienteService extends GenericService<Cliente,Long> {
     Cliente cambiarActivo(Long id);
}

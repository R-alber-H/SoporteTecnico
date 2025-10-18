package com.empresaservicios.soporte.service;

import com.empresaservicios.soporte.entity.Tecnico;

public interface TecnicoService extends GenericService<Tecnico,Long> {
    Tecnico cambiarActivo(Long id);
}

package com.empresaservicios.soporte.service;

import com.empresaservicios.soporte.dto.AsignacionCreateDTO;
import com.empresaservicios.soporte.dto.AsignacionDTO;
import com.empresaservicios.soporte.entity.Asignacion;

public interface AsignacionService extends GenericService<Asignacion,Long> {

    AsignacionDTO crear(AsignacionCreateDTO dto);
}

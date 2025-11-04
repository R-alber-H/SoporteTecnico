package com.empresaservicios.soporte.service;

import java.util.Map;

import com.empresaservicios.soporte.dto.TecnicoUpdateDTO;
import com.empresaservicios.soporte.entity.Tecnico;

public interface TecnicoService extends GenericService<Tecnico,Long> {
    Tecnico cambiarActivo(Long id);
    Tecnico actualizarDatos(Long id , TecnicoUpdateDTO dto);
}

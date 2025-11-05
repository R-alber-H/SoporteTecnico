package com.empresaservicios.soporte.service;

import com.empresaservicios.soporte.dto.AsignacionCreateDTO;
import com.empresaservicios.soporte.dto.AsignacionDTO;
import com.empresaservicios.soporte.dto.AsignacionUpdateDTO;
import com.empresaservicios.soporte.entity.Asignacion;
import jakarta.validation.Valid;

import java.util.List;

public interface AsignacionService extends GenericService<Asignacion,Long> {

    AsignacionDTO crear(AsignacionCreateDTO dto);

    List<AsignacionDTO> listarTodos();

    AsignacionDTO encontrarPorId(Long id);

    AsignacionDTO actualizar(Long id, AsignacionUpdateDTO asignacion);
}

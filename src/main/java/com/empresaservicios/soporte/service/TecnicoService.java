package com.empresaservicios.soporte.service;

import java.util.List;
import java.util.Map;

import com.empresaservicios.soporte.dto.TecnicoCreateDTO;
import com.empresaservicios.soporte.dto.TecnicoDTO;
import com.empresaservicios.soporte.dto.TecnicoUpdateDTO;
import com.empresaservicios.soporte.entity.Tecnico;

public interface TecnicoService extends GenericService<Tecnico,Long> {
    TecnicoDTO cambiarActivo(Long id);
    TecnicoDTO actualizarDatos(Long id , TecnicoUpdateDTO dto);
    TecnicoDTO crear(TecnicoCreateDTO tecnico);
    List<TecnicoDTO> listarTodos();
    TecnicoDTO buscarPorId(Long id);
}

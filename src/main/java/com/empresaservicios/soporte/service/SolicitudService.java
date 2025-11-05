package com.empresaservicios.soporte.service;

import com.empresaservicios.soporte.dto.SolicitudCreateDTO;
import com.empresaservicios.soporte.dto.SolicitudDTO;
import com.empresaservicios.soporte.dto.SolicitudUpdateDTO;
import com.empresaservicios.soporte.entity.Solicitud;

import java.util.List;

public interface SolicitudService extends GenericService<Solicitud,Long> {
    SolicitudDTO cambiarActivo (Long id);
    SolicitudDTO marcarResuelto(Long id);
    SolicitudDTO marcarNoResuelto(Long id);
    SolicitudDTO marcarCancelado(Long id);
    SolicitudDTO crear(SolicitudCreateDTO dto);
    SolicitudDTO buscarPorId(Long id);
    List<SolicitudDTO> buscarTodos();
    SolicitudDTO actualizar(Long id, SolicitudUpdateDTO solicitud);
}

package com.empresaservicios.soporte.service;

import com.empresaservicios.soporte.entity.Solicitud;

public interface SolicitudService extends GenericService<Solicitud,Long> {
    Solicitud cambiarActivo (Long id);
    Solicitud marcarResuelto(Long id);
    Solicitud marcarNoResuelto(Long id);
}

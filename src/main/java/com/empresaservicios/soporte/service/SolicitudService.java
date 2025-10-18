package com.empresaservicios.soporte.service;

import com.empresaservicios.soporte.entity.Solicitud;

public interface SolicitudService extends GenericService<Solicitud,Long> {
    Solicitud cambiarActivo (Long id);
}

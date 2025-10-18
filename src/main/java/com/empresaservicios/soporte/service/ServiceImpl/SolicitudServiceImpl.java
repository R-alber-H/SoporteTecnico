package com.empresaservicios.soporte.service.ServiceImpl;

import org.springframework.stereotype.Service;

import com.empresaservicios.soporte.entity.Solicitud;
import com.empresaservicios.soporte.repository.SolicitudRepository;
import com.empresaservicios.soporte.service.SolicitudService;
import com.empresaservicios.soporte.utils.enums.Activo;

@Service
public class SolicitudServiceImpl extends GenericServiceImpl<Solicitud, Long> implements SolicitudService {

    private final SolicitudRepository solicitudRepository;

    public SolicitudServiceImpl(SolicitudRepository solicitudRepository) {
        super(solicitudRepository);
        this.solicitudRepository = solicitudRepository;
    }

    @Override
    public Solicitud cambiarActivo(Long id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicictud no encontrada"));
        solicitud.setActivo(solicitud.getActivo() == Activo.Si ? Activo.No : Activo.Si);
        return solicitudRepository.save(solicitud);
    }
}

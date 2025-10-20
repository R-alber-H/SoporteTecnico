package com.empresaservicios.soporte.service.ServiceImpl;

import org.springframework.stereotype.Service;

import com.empresaservicios.soporte.entity.Solicitud;
import com.empresaservicios.soporte.exception.SolicitudNoEncontradaException;
import com.empresaservicios.soporte.repository.SolicitudRepository;
import com.empresaservicios.soporte.service.SolicitudService;
import com.empresaservicios.soporte.utils.enums.Activo;
import com.empresaservicios.soporte.utils.enums.Estado;

@Service
public class SolicitudServiceImpl extends GenericServiceImpl<Solicitud, Long> implements SolicitudService {

    private final SolicitudRepository solicitudRepository;

    public SolicitudServiceImpl(SolicitudRepository solicitudRepository) {
        super(solicitudRepository,"Solicitud");
        this.solicitudRepository = solicitudRepository;
    }

    @Override
    public Solicitud cambiarActivo(Long id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new SolicitudNoEncontradaException("Solicictud no encontrada"));
        solicitud.setActivo(solicitud.getActivo() == Activo.Si ? Activo.No : Activo.Si);
        return solicitudRepository.save(solicitud);
    }

    @Override
    public Solicitud marcarResuelto(Long id){
        Solicitud solicitud = solicitudRepository.findById(id)
            .orElseThrow(() -> new SolicitudNoEncontradaException("Solicictud no encontrada"));
        solicitud.setEstado(Estado.Resuelto);
       return solicitudRepository.save(solicitud);
        
    }

    @Override
    public Solicitud marcarNoResuelto(Long id){
       Solicitud solicitud = solicitudRepository.findById(id)
            .orElseThrow(() -> new SolicitudNoEncontradaException("Solicictud no encontrada"));
        solicitud.setEstado(Estado.No_resuelto);
       return solicitudRepository.save(solicitud); 
    }
}

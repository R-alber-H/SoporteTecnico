package com.empresaservicios.soporte.service.ServiceImpl;

import org.springframework.stereotype.Service;

import com.empresaservicios.soporte.entity.Cliente;
import com.empresaservicios.soporte.entity.Solicitud;
import com.empresaservicios.soporte.exception.SolicitudNoEncontradaException;
import com.empresaservicios.soporte.exception.UsuarioNoEncontradoException;
import com.empresaservicios.soporte.repository.ClienteRepository;
import com.empresaservicios.soporte.repository.SolicitudRepository;
import com.empresaservicios.soporte.service.SolicitudService;
import com.empresaservicios.soporte.utils.enums.Activo;
import com.empresaservicios.soporte.utils.enums.Estado;

@Service
public class SolicitudServiceImpl extends GenericServiceImpl<Solicitud, Long> implements SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final ClienteRepository clienteRepository;

    public SolicitudServiceImpl(SolicitudRepository solicitudRepository, ClienteRepository clienteRepository) {
        super(solicitudRepository,"Solicitud");
        this.solicitudRepository = solicitudRepository;
        this.clienteRepository =clienteRepository;
    }

    public Solicitud save(Solicitud solicitud) {
        Long clienteId = solicitud.getCliente().getId();
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Cliente no encontrado"));
        solicitud.setCliente(cliente);
        return solicitudRepository.save(solicitud);
    }

    @Override
    public Solicitud cambiarActivo(Long id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new SolicitudNoEncontradaException("Solicictud no encontrada"));
        solicitud.setActivo(solicitud.getActivo() == Activo.SI ? Activo.NO : Activo.SI);
        return solicitudRepository.save(solicitud);
    }

    @Override
    public Solicitud marcarResuelto(Long id){
        return cambiarEstadoSolicitud(id, Estado.RESUELTO);
        
    }

    @Override
    public Solicitud marcarNoResuelto(Long id){
        return cambiarEstadoSolicitud(id, Estado.NO_RESUELTO);
    }

    @Override
    public Solicitud marcarCancelado(Long id){
        return cambiarEstadoSolicitud(id, Estado.CANCELADO);
    }

    private Solicitud cambiarEstadoSolicitud(Long id, Estado nuevoEstado){
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new SolicitudNoEncontradaException("Solicitud no encontrada"));
        solicitud.setEstado(nuevoEstado);
        return solicitudRepository.save(solicitud);
    }
}

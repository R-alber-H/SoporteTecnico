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

    // todo: crear un solo metodo que reciba el nuevo valor del estado ejm: cambiarEstadoSolicitud
    // todo: o pueden refactorizar en un solo metodo
    // private cambiarEstado(Long id, Enum estado)
    @Override
    public Solicitud marcarResuelto(Long id){
        Solicitud solicitud = solicitudRepository.findById(id)
            .orElseThrow(() -> new SolicitudNoEncontradaException("Solicictud no encontrada"));
        solicitud.setEstado(Estado.RESUELTO);
       return solicitudRepository.save(solicitud);
        
    }

    @Override
    public Solicitud marcarNoResuelto(Long id){
       Solicitud solicitud = solicitudRepository.findById(id)
            .orElseThrow(() -> new SolicitudNoEncontradaException("Solicictud no encontrada"));
        solicitud.setEstado(Estado.NO_RESUELTO);
       return solicitudRepository.save(solicitud); 
    }

    @Override
    public Solicitud marcarCancelado(Long id){
        Solicitud solicitud = solicitudRepository.findById(id)
        .orElseThrow(()-> new SolicitudNoEncontradaException("solicitud no encontrada"));
        solicitud.setEstado(Estado.CANCELADO);
        return solicitudRepository.save(solicitud);
    }
}

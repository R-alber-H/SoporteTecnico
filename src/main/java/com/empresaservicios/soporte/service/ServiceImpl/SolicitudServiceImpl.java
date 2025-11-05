package com.empresaservicios.soporte.service.ServiceImpl;

import com.empresaservicios.soporte.dto.SolicitudCreateDTO;
import com.empresaservicios.soporte.dto.SolicitudDTO;
import com.empresaservicios.soporte.dto.SolicitudUpdateDTO;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SolicitudServiceImpl extends GenericServiceImpl<Solicitud, Long> implements SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final ClienteRepository clienteRepository;

    public SolicitudServiceImpl(SolicitudRepository solicitudRepository, ClienteRepository clienteRepository) {
        super(solicitudRepository,"Solicitud");
        this.solicitudRepository = solicitudRepository;
        this.clienteRepository =clienteRepository;
    }

    @Override
    @Transactional
    public SolicitudDTO cambiarActivo(Long id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new SolicitudNoEncontradaException("Solicictud no encontrada"));

        solicitud.setActivo(solicitud.getActivo() == Activo.SI ? Activo.NO : Activo.SI);

        Solicitud guardada = solicitudRepository.save(solicitud);

        return new SolicitudDTO(
                guardada.getId(),
                guardada.getAsunto(),
                guardada.getDescripcion(),
                guardada.getEstado(),
                guardada.getActivo(),
                guardada.getCliente().getId(),
                guardada.getMotivoRechazo()
        );
    }

    @Override
    @Transactional
    public SolicitudDTO marcarResuelto(Long id){
        return cambiarEstadoSolicitud(id, Estado.RESUELTO);
        
    }

    @Override
    @Transactional
    public SolicitudDTO marcarNoResuelto(Long id){
        return cambiarEstadoSolicitud(id, Estado.NO_RESUELTO);
    }

    @Override
    @Transactional
    public SolicitudDTO marcarCancelado(Long id){
        return cambiarEstadoSolicitud(id, Estado.CANCELADO);
    }

    @Override
    @Transactional
    public SolicitudDTO crear(SolicitudCreateDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new UsuarioNoEncontradoException("Cliente no encontrado"));

        Solicitud solicitud = Solicitud.builder()
                .asunto(dto.asunto())
                .activo(Activo.SI)
                .estado(Estado.PENDIENTE)
                .descripcion(dto.descripcion())
                .cliente(cliente)
                .build();

        Solicitud guardada = solicitudRepository.save(solicitud);

        return new SolicitudDTO(
                guardada.getId(),
                guardada.getAsunto(),
                guardada.getDescripcion(),
                guardada.getEstado(),
                guardada.getActivo(),
                guardada.getCliente().getId(),
                guardada.getMotivoRechazo()
        );
    }

    @Override
    public SolicitudDTO buscarPorId(Long id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new SolicitudNoEncontradaException("Solicitud no encontrada con ID " + id));

        return new SolicitudDTO(
                solicitud.getId(),
                solicitud.getAsunto(),
                solicitud.getDescripcion(),
                solicitud.getEstado(),
                solicitud.getActivo(),
                solicitud.getCliente().getId(),
                solicitud.getMotivoRechazo()
        );
    }

    @Override
    public List<SolicitudDTO> buscarTodos() {
        List<Solicitud> solicitudes = solicitudRepository.findAll();

        return solicitudes.stream()
                .map(solicitud -> new SolicitudDTO(
                        solicitud.getId(),
                        solicitud.getAsunto(),
                        solicitud.getDescripcion(),
                        solicitud.getEstado(),
                        solicitud.getActivo(),
                        solicitud.getCliente().getId(),
                        solicitud.getMotivoRechazo()
                ))
                .toList();
    }

    @Override
    @Transactional
    public SolicitudDTO actualizar(Long id, SolicitudUpdateDTO dto) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new SolicitudNoEncontradaException("Solicitud no encontrada con ID " + id));

        solicitud.setAsunto(dto.asunto());
        solicitud.setDescripcion(dto.descripcion());

        Solicitud guardada = solicitudRepository.save(solicitud);

        return new SolicitudDTO(
                guardada.getId(),
                guardada.getAsunto(),
                guardada.getDescripcion(),
                guardada.getEstado(),
                guardada.getActivo(),
                guardada.getCliente().getId(),
                guardada.getMotivoRechazo()
        );
    }

    private SolicitudDTO cambiarEstadoSolicitud(Long id, Estado nuevoEstado){
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new SolicitudNoEncontradaException("Solicitud no encontrada"));

        solicitud.setEstado(nuevoEstado);

        Solicitud guardada = solicitudRepository.save(solicitud);

        return new SolicitudDTO(
                guardada.getId(),
                guardada.getAsunto(),
                guardada.getDescripcion(),
                guardada.getEstado(),
                guardada.getActivo(),
                guardada.getCliente().getId(),
                guardada.getMotivoRechazo()
        );
    }
}

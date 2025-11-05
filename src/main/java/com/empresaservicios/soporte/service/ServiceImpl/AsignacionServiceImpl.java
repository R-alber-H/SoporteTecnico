package com.empresaservicios.soporte.service.ServiceImpl;

import com.empresaservicios.soporte.dto.AsignacionCreateDTO;
import com.empresaservicios.soporte.dto.AsignacionDTO;
import org.springframework.stereotype.Service;

import com.empresaservicios.soporte.entity.Asignacion;
import com.empresaservicios.soporte.entity.Solicitud;
import com.empresaservicios.soporte.entity.Tecnico;
import com.empresaservicios.soporte.exception.SolicitudNoEncontradaException;
import com.empresaservicios.soporte.exception.UsuarioNoEncontradoException;
import com.empresaservicios.soporte.repository.AsignacionRepository;
import com.empresaservicios.soporte.repository.SolicitudRepository;
import com.empresaservicios.soporte.repository.TecnicoRepository;
import com.empresaservicios.soporte.service.AsignacionService;
import com.empresaservicios.soporte.utils.enums.Estado;

@Service
public class AsignacionServiceImpl extends GenericServiceImpl<Asignacion, Long> implements AsignacionService {

    private final AsignacionRepository asignacionRepository;
    private final TecnicoRepository tecnicoRepository;
    private final SolicitudRepository solicitudRepository;

    public AsignacionServiceImpl(AsignacionRepository asignacionRepository, TecnicoRepository tecnicoRepository,
            SolicitudRepository solicitudRepository) {

        super(asignacionRepository, "Asignacion");
        this.asignacionRepository = asignacionRepository;
        this.tecnicoRepository = tecnicoRepository;
        this.solicitudRepository = solicitudRepository;
    }

    @Override
    public Asignacion save(Asignacion asignacion) {
        Tecnico tecnico = tecnicoRepository.findById(asignacion.getTecnico().getId())
                .orElseThrow(() -> new UsuarioNoEncontradoException("Técnico no encontrado"));

        Solicitud solicitud = solicitudRepository.findById(asignacion.getSolicitud().getId())
                .orElseThrow(() -> new SolicitudNoEncontradaException("Solicitud no encontrada"));

        Estado estado = solicitud.getEstado();

        if (estado == Estado.RESUELTO || estado == Estado.NO_RESUELTO || estado == Estado.CANCELADO) {
            throw new RuntimeException("No se puede asignar la solicitud");
        }

        if (estado == Estado.PENDIENTE) {
            solicitud.setEstado(Estado.ASIGNADO);
            solicitudRepository.save(solicitud);
        }
        asignacion.setTecnico(tecnico);
        asignacion.setSolicitud(solicitud);
        return asignacionRepository.save(asignacion);
    }

    @Override
    public AsignacionDTO crear(AsignacionCreateDTO dto) {
        Tecnico tecnico = tecnicoRepository.findById(dto.tecnicoId())
                .orElseThrow(() -> new UsuarioNoEncontradoException("Técnico no encontrado"));

        Solicitud solicitud = solicitudRepository.findById(dto.solicitudId())
                .orElseThrow(() -> new SolicitudNoEncontradaException("Solicitud no encontrada"));

        Asignacion asignacion = Asignacion.builder()
                .tecnico(tecnico)
                .solicitud(solicitud)
                .build();

        Asignacion guardada = asignacionRepository.save(asignacion);

        return new AsignacionDTO(
                guardada.getId(),
                tecnico.getId(),
                tecnico.getDatosPersona().getNombres() + " " + tecnico.getDatosPersona().getApellidos(),
                solicitud.getId(),
                solicitud.getAsunto()
        );
    }
}

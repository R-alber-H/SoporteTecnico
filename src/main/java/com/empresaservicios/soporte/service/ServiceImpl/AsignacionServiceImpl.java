package com.empresaservicios.soporte.service.ServiceImpl;

import com.empresaservicios.soporte.dto.AsignacionCreateDTO;
import com.empresaservicios.soporte.dto.AsignacionDTO;
import com.empresaservicios.soporte.dto.AsignacionUpdateDTO;
import com.empresaservicios.soporte.exception.AsignacionNoPendienteException;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Transactional
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

    @Override
    public List<AsignacionDTO> listarTodos() {
        List<Asignacion> asignaciones = asignacionRepository.findAll();

        return asignaciones.stream()
                .map(a -> new AsignacionDTO(
                        a.getId(),
                        a.getTecnico().getId(),
                        a.getTecnico().getDatosPersona().getNombres() + " " + a.getTecnico().getDatosPersona().getApellidos(),
                        a.getSolicitud().getId(),
                        a.getSolicitud().getAsunto()
                ))
                .toList();
    }

    @Override
    public AsignacionDTO encontrarPorId(Long id) {
        Asignacion asignacion = asignacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada con ID " + id));

        return new AsignacionDTO(
                asignacion.getId(),
                asignacion.getTecnico().getId(),
                asignacion.getTecnico().getDatosPersona().getNombres() + " " + asignacion.getTecnico().getDatosPersona().getApellidos(),
                asignacion.getSolicitud().getId(),
                asignacion.getSolicitud().getAsunto()
        );
    }

    @Override
    @Transactional
    public AsignacionDTO actualizar(Long id, AsignacionUpdateDTO dto) {
        Asignacion asignacion = asignacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada con ID " + id));

        Tecnico tecnico = tecnicoRepository.findById(dto.tecnicoId())
                .orElseThrow(() -> new UsuarioNoEncontradoException("Técnico no encontrado"));

        Solicitud solicitud = solicitudRepository.findById(dto.solicitudId())
                .orElseThrow(() -> new SolicitudNoEncontradaException("Solicitud no encontrada"));

        Estado estado = solicitud.getEstado();
        if (estado == Estado.RESUELTO || estado == Estado.NO_RESUELTO || estado == Estado.CANCELADO) {
            throw new AsignacionNoPendienteException("Solo se pueden actualizar las asignaciones que estan pendientes");
        }

        asignacion.setTecnico(tecnico);
        asignacion.setSolicitud(solicitud);

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

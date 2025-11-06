package com.empresaservicios.soporte.service.ServiceImpl;

import java.util.List;

import com.empresaservicios.soporte.dto.DatosPersonaDTO;
import com.empresaservicios.soporte.dto.TecnicoCreateDTO;
import com.empresaservicios.soporte.dto.TecnicoDTO;
import com.empresaservicios.soporte.dto.TecnicoUpdateDTO;
import com.empresaservicios.soporte.exception.DniUsadoException;
import org.springframework.stereotype.Service;

import com.empresaservicios.soporte.entity.DatosPersona;
import com.empresaservicios.soporte.entity.Tecnico;
import com.empresaservicios.soporte.exception.UsuarioNoEncontradoException;
import com.empresaservicios.soporte.repository.DatosPersonaRepository;
import com.empresaservicios.soporte.repository.TecnicoRepository;
import com.empresaservicios.soporte.service.TecnicoService;
import com.empresaservicios.soporte.utils.enums.Activo;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TecnicoServiceImpl extends GenericServiceImpl<Tecnico, Long> implements TecnicoService {

    private final TecnicoRepository tecnicoRepository;
    private final DatosPersonaRepository datosPersonaRepository;

    public TecnicoServiceImpl(TecnicoRepository tecnicoRepository, DatosPersonaRepository datosPersonaRepository) {
        super(tecnicoRepository, "Tecnico");
        this.tecnicoRepository = tecnicoRepository;
        this.datosPersonaRepository = datosPersonaRepository;
    }

    @Override
    @Transactional
    public TecnicoDTO cambiarActivo(Long id) {
        Tecnico tecnico = tecnicoRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Tecnico no encontrado"));

        tecnico.setActivo(tecnico.getActivo() == Activo.SI ? Activo.NO : Activo.SI);

        Tecnico guardado = tecnicoRepository.save(tecnico);

        return new TecnicoDTO(
                guardado.getId(),
                guardado.getActivo(),
                new DatosPersonaDTO(
                        guardado.getDatosPersona().getNombres(),
                        guardado.getDatosPersona().getApellidos(),
                        guardado.getDatosPersona().getDni(),
                        guardado.getDatosPersona().getTelefono()
                )
        );
    }

    @Override
    @Transactional
    public TecnicoDTO actualizarDatos(Long id, TecnicoUpdateDTO dto) {

        Tecnico tecnico = tecnicoRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Tecnico no encontrado"));

        DatosPersona persona = tecnico.getDatosPersona();

        if (persona == null) {
            throw new UsuarioNoEncontradoException("El técnico no tiene datos personales asociados");
        }

        if (dto.nombres() != null && !dto.nombres().isBlank()) {
            persona.setNombres(dto.nombres());
        }

        if (dto.apellidos() != null && !dto.apellidos().isBlank()) {
            persona.setApellidos(dto.apellidos());
        }

        if (dto.telefono() != null && !dto.telefono().isBlank()) {
            persona.setTelefono(dto.telefono());
        }

        datosPersonaRepository.save(persona);

        return new TecnicoDTO(
                tecnico.getId(),
                tecnico.getActivo(),
                new DatosPersonaDTO(
                        persona.getNombres(),
                        persona.getApellidos(),
                        persona.getDni(),
                        persona.getTelefono()
                )
        );
    }

    @Override
    @Transactional
    public TecnicoDTO crear(TecnicoCreateDTO dto) {
        if (dto.datosPersona() == null) {
            throw new IllegalArgumentException("Los datos personales del técnico son obligatorios");
        }

        Boolean existeDni = datosPersonaRepository.existsByDniIgnoreCaseAllIgnoreCase(dto.datosPersona().dni());

        if (existeDni) {
            throw new DniUsadoException("El dni ya esta siendo usado por otro cliente/tecnico");
        }

        Tecnico tecnico = Tecnico.builder()
                .activo(Activo.SI)
                .datosPersona(DatosPersona.builder()
                        .nombres(dto.datosPersona().nombres())
                        .apellidos(dto.datosPersona().apellidos())
                        .dni(dto.datosPersona().dni())
                        .telefono(dto.datosPersona().telefono())
                        .build())
                .build();

        Tecnico guardado = tecnicoRepository.save(tecnico);

        return new TecnicoDTO(
                guardado.getId(),
                guardado.getActivo(),
                new DatosPersonaDTO(
                        guardado.getDatosPersona().getNombres(),
                        guardado.getDatosPersona().getApellidos(),
                        guardado.getDatosPersona().getDni(),
                        guardado.getDatosPersona().getTelefono()
                )
        );
    }

    @Override
    public List<TecnicoDTO> listarTodos() {
        List<Tecnico> tecnicos = tecnicoRepository.findAll();

        return tecnicos.stream()
                .map(t -> new TecnicoDTO(
                        t.getId(),
                        t.getActivo(),
                        new DatosPersonaDTO(
                                t.getDatosPersona().getNombres(),
                                t.getDatosPersona().getApellidos(),
                                t.getDatosPersona().getDni(),
                                t.getDatosPersona().getTelefono()
                        )
                ))
                .toList();
    }

    @Override
    public TecnicoDTO buscarPorId(Long id) {
        Tecnico tecnico = tecnicoRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Tecnico no encontrado con id " + id));

        DatosPersona datos = tecnico.getDatosPersona();

        return new TecnicoDTO(
                tecnico.getId(),
                tecnico.getActivo(),
                new DatosPersonaDTO(
                        datos.getNombres(),
                        datos.getApellidos(),
                        datos.getDni(),
                        datos.getTelefono()
                )
        );
    }
}

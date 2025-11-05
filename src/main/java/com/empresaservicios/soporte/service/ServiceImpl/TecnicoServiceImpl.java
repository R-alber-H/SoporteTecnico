package com.empresaservicios.soporte.service.ServiceImpl;

import java.util.Map;

import com.empresaservicios.soporte.dto.DatosPersonaDTO;
import com.empresaservicios.soporte.dto.TecnicoCreateDTO;
import com.empresaservicios.soporte.dto.TecnicoDTO;
import com.empresaservicios.soporte.dto.TecnicoUpdateDTO;
import org.springframework.stereotype.Service;

import com.empresaservicios.soporte.entity.DatosPersona;
import com.empresaservicios.soporte.entity.Tecnico;
import com.empresaservicios.soporte.exception.UsuarioNoEncontradoException;
import com.empresaservicios.soporte.repository.DatosPersonaRepository;
import com.empresaservicios.soporte.repository.TecnicoRepository;
import com.empresaservicios.soporte.service.TecnicoService;
import com.empresaservicios.soporte.utils.enums.Activo;

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
    public Tecnico cambiarActivo(Long id) {
        Tecnico tecnico = tecnicoRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Tecnico no encontrado"));

        tecnico.setActivo(tecnico.getActivo() == Activo.SI ? Activo.NO : Activo.SI);
        return tecnicoRepository.save(tecnico);
    }

    @Override
    public Tecnico actualizarDatos(Long id, TecnicoUpdateDTO dto) {

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
        return tecnico;
    }

    @Override
    public TecnicoDTO crear(TecnicoCreateDTO dto) {
        if (dto.datosPersona() == null) {
            throw new IllegalArgumentException("Los datos personales del técnico son obligatorios");
        }

        Tecnico tecnico = Tecnico.builder()
                .activo(Activo.SI) // valor por defecto
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
}

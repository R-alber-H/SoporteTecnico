package com.empresaservicios.soporte.service.ServiceImpl;

import java.util.Map;

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
    public Tecnico actualizarDatos(Long id, Map<String, Object> datos) {
        Tecnico tecnico = tecnicoRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Tecnico no encontrado"));
        DatosPersona persona = tecnico.getDatosPersona();
        if (persona == null) {
            throw new RuntimeException("El técnico no tiene datos personales asociados");
        }
        if (datos.containsKey("nombres")) {
            persona.setNombres((String) datos.get("nombres"));
        }
        if (datos.containsKey("telefono")) {
            persona.setTelefono((String) datos.get("telefono"));
        }
        datosPersonaRepository.save(persona);
        return tecnicoRepository.findById(id).get();
    }

}

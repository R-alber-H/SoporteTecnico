package com.empresaservicios.soporte.service.ServiceImpl;

import org.springframework.stereotype.Service;

import com.empresaservicios.soporte.entity.Tecnico;
import com.empresaservicios.soporte.repository.TecnicoRepository;
import com.empresaservicios.soporte.service.TecnicoService;
import com.empresaservicios.soporte.utils.enums.Activo;

@Service
public class TecnicoServiceImpl extends GenericServiceImpl<Tecnico,Long> implements TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    public TecnicoServiceImpl(TecnicoRepository tecnicoRepository){
        super(tecnicoRepository);
        this.tecnicoRepository = tecnicoRepository;
        
    }

    @Override
        public Tecnico cambiarActivo(Long id) {
        Tecnico tecnico = tecnicoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tecnico no encontrado"));

        tecnico.setActivo(tecnico.getActivo() == Activo.Si ? Activo.No : Activo.Si);
        return tecnicoRepository.save(tecnico);
    }
    
}

package com.empresaservicios.soporte.service.ServiceImpl;

import org.springframework.stereotype.Service;

import com.empresaservicios.soporte.entity.DatosPersona;
import com.empresaservicios.soporte.repository.DatosPersonaRepository;
import com.empresaservicios.soporte.service.DatosPersonaService;

@Service
public class DatosPersonaServiceImpl extends GenericServiceImpl<DatosPersona,Long> implements DatosPersonaService {
  
    public DatosPersonaServiceImpl(DatosPersonaRepository datosPersonaRepository){
        super(datosPersonaRepository,"DatosPersona");
    }
}

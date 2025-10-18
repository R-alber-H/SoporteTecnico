package com.empresaservicios.soporte.service.ServiceImpl;

import org.springframework.stereotype.Service;

import com.empresaservicios.soporte.entity.Asignacion;
import com.empresaservicios.soporte.repository.AsignacionRepository;
import com.empresaservicios.soporte.service.AsignacionService;

@Service
public class AsignacionServiceImpl extends GenericServiceImpl<Asignacion,Long> implements AsignacionService {

    public AsignacionServiceImpl(AsignacionRepository asignacionRepository){
        super(asignacionRepository);
    }
    
}

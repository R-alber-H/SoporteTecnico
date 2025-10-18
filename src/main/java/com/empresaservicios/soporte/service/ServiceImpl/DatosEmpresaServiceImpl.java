package com.empresaservicios.soporte.service.ServiceImpl;

import org.springframework.stereotype.Service;

import com.empresaservicios.soporte.entity.DatosEmpresa;
import com.empresaservicios.soporte.repository.DatosEmpresaRepository;
import com.empresaservicios.soporte.service.DatosEmpresaService;

@Service
public class DatosEmpresaServiceImpl extends GenericServiceImpl<DatosEmpresa,Long> implements DatosEmpresaService {
    
    public DatosEmpresaServiceImpl(DatosEmpresaRepository datosEmpresaRepository){
        super(datosEmpresaRepository);
    }
}

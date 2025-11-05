package com.empresaservicios.soporte.service;

import java.util.Map;

import com.empresaservicios.soporte.dto.ClienteCreateDTO;
import com.empresaservicios.soporte.dto.ClienteDTO;
import com.empresaservicios.soporte.entity.Cliente;

public interface ClienteService extends GenericService<Cliente,Long> {
     Cliente cambiarActivo(Long id);
     Cliente actualizarDatos(Long id , Map<String,Object> datos);
     ClienteDTO crear(ClienteCreateDTO cliente);
}

package com.empresaservicios.soporte.service;

import java.util.List;

import com.empresaservicios.soporte.dto.ClienteCreateDTO;
import com.empresaservicios.soporte.dto.ClienteDTO;
import com.empresaservicios.soporte.dto.ClienteUpdateDTO;
import com.empresaservicios.soporte.entity.Cliente;

public interface ClienteService extends GenericService<Cliente, Long> {
    ClienteDTO cambiarActivo(Long id);

    ClienteDTO actualizarDatos(Long id, ClienteUpdateDTO dto);

    ClienteDTO crear(ClienteCreateDTO cliente);

    ClienteDTO buscarPorId(Long id);

    List<ClienteDTO> listarTodos();
}

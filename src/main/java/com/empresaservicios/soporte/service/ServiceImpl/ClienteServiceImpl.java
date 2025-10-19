package com.empresaservicios.soporte.service.ServiceImpl;

import org.springframework.stereotype.Service;

import com.empresaservicios.soporte.entity.Cliente;
import com.empresaservicios.soporte.repository.ClienteRepository;
import com.empresaservicios.soporte.service.ClienteService;
import com.empresaservicios.soporte.utils.enums.Activo;

@Service
public class ClienteServiceImpl extends GenericServiceImpl<Cliente,Long> implements ClienteService {
    
     private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository){
        super(clienteRepository,"Cliente");  
        this.clienteRepository = clienteRepository;   
    }

    @Override
        public Cliente cambiarActivo(Long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.setActivo(cliente.getActivo() == Activo.Si ? Activo.No : Activo.Si);
        return clienteRepository.save(cliente);
    }
}

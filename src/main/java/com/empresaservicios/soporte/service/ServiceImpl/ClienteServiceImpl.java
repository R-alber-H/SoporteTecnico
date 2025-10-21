package com.empresaservicios.soporte.service.ServiceImpl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.empresaservicios.soporte.entity.Cliente;
import com.empresaservicios.soporte.entity.DatosEmpresa;
import com.empresaservicios.soporte.entity.DatosPersona;
import com.empresaservicios.soporte.exception.UsuarioNoEncontradoException;
import com.empresaservicios.soporte.repository.ClienteRepository;
import com.empresaservicios.soporte.repository.DatosEmpresaRepository;
import com.empresaservicios.soporte.repository.DatosPersonaRepository;
import com.empresaservicios.soporte.service.ClienteService;
import com.empresaservicios.soporte.utils.enums.Activo;
import com.empresaservicios.soporte.utils.enums.TipoCliente;

@Service
public class ClienteServiceImpl extends GenericServiceImpl<Cliente, Long> implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final DatosPersonaRepository datosPersonaRepository;
    private final DatosEmpresaRepository datosEmpresaRepository;

     public ClienteServiceImpl( ClienteRepository clienteRepository, DatosPersonaRepository datosPersonaRepository, DatosEmpresaRepository datosEmpresaRepository ) {
        super(clienteRepository, "Cliente");
        this.clienteRepository = clienteRepository;
        this.datosPersonaRepository = datosPersonaRepository;
        this.datosEmpresaRepository = datosEmpresaRepository;
    }

    @Override
    public Cliente cambiarActivo(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Cliente no encontrado"));

        cliente.setActivo(cliente.getActivo() == Activo.SI ? Activo.NO : Activo.SI);
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente actualizarDatos(Long id, Map<String, Object> datos) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Cliente no encontrado"));
        if (cliente.getTipoCliente() == TipoCliente.PERSONA) {
            DatosPersona persona = cliente.getDatosPersona();
            if (persona == null) {
                throw new RuntimeException("El cliente no tiene datos de persona asociados");
            }
            if (datos.containsKey("nombres")) {
                persona.setNombres((String) datos.get("nombres"));
            }
            if (datos.containsKey("telefono")) {
                persona.setTelefono((String) datos.get("telefono"));
            }
            datosPersonaRepository.save(persona);

        } else if (cliente.getTipoCliente() == TipoCliente.EMPRESA) {
            DatosEmpresa empresa = cliente.getDatosEmpresa();
            if (empresa == null) {
                throw new RuntimeException("El cliente no tiene datos de empresa asociados");
            }
            if (datos.containsKey("razonSocial")) {
                empresa.setRazonSocial((String) datos.get("razonSocial"));
            }
            if (datos.containsKey("telefono")) {
                empresa.setTelefono((String) datos.get("telefono"));
            }
            datosEmpresaRepository.save(empresa);
        }
        return repository.findById(id).get();
    }
}

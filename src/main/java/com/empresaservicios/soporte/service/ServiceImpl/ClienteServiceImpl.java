package com.empresaservicios.soporte.service.ServiceImpl;

import java.util.Map;

import com.empresaservicios.soporte.dto.ClienteCreateDTO;
import com.empresaservicios.soporte.dto.ClienteDTO;
import com.empresaservicios.soporte.dto.DatosEmpresaDTO;
import com.empresaservicios.soporte.dto.DatosPersonaDTO;
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
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public ClienteDTO crear(ClienteCreateDTO dto) {
        Cliente cliente;

        if (dto.tipoCliente() == TipoCliente.PERSONA) {
            if (dto.datosPersona() == null) {
                throw new IllegalArgumentException("Debe enviar datosPersona para cliente tipo PERSONA");
            }

            cliente = Cliente.builder()
                    .tipoCliente(dto.tipoCliente())
                    .activo(Activo.SI)
                    .datosPersona(DatosPersona.builder()
                            .nombres(dto.datosPersona().nombres())
                            .apellidos(dto.datosPersona().apellidos())
                            .dni(dto.datosPersona().dni())
                            .telefono(dto.datosPersona().telefono())
                            .build())
                    .build();

        } else if (dto.tipoCliente() == TipoCliente.EMPRESA) {
            if (dto.datosEmpresa() == null) {
                throw new IllegalArgumentException("Debe enviar datosEmpresa para cliente tipo EMPRESA");
            }

            cliente = Cliente.builder()
                    .tipoCliente(dto.tipoCliente())
                    .activo(Activo.SI)
                    .datosEmpresa(DatosEmpresa.builder()
                            .razonSocial(dto.datosEmpresa().razonSocial())
                            .ruc(dto.datosEmpresa().ruc())
                            .telefono(dto.datosEmpresa().telefono())
                            .build())
                    .build();

        } else {
            throw new IllegalArgumentException("Tipo de cliente no válido");
        }

        Cliente guardado = clienteRepository.save(cliente);

        return new ClienteDTO(
                guardado.getId(),
                guardado.getTipoCliente(),
                guardado.getActivo(),
                guardado.getDatosPersona() != null ?
                        new DatosPersonaDTO(
                                guardado.getDatosPersona().getNombres(),
                                guardado.getDatosPersona().getApellidos(),
                                guardado.getDatosPersona().getDni(),
                                guardado.getDatosPersona().getTelefono()
                        ) : null,
                guardado.getDatosEmpresa() != null ?
                        new DatosEmpresaDTO(
                                guardado.getDatosEmpresa().getRazonSocial(),
                                guardado.getDatosEmpresa().getRuc(),
                                guardado.getDatosEmpresa().getTelefono()
                        ) : null
        );
    }
}

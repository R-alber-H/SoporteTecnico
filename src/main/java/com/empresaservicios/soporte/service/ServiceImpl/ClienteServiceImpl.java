package com.empresaservicios.soporte.service.ServiceImpl;

import java.util.List;
import java.util.Map;

import com.empresaservicios.soporte.dto.*;
import com.empresaservicios.soporte.exception.DniUsadoException;
import com.empresaservicios.soporte.exception.RucUsadoException;
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
    @Transactional
    public ClienteDTO cambiarActivo(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Cliente no encontrado"));

        cliente.setActivo(cliente.getActivo() == Activo.SI ? Activo.NO : Activo.SI);

        Cliente actualizado = clienteRepository.save(cliente);

        return toDTO(actualizado);
    }

    @Override
    @Transactional
    public ClienteDTO actualizarDatos(Long id, ClienteUpdateDTO dto) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Cliente no encontrado"));

        if (cliente.getTipoCliente() == TipoCliente.PERSONA) {
            if (dto.datosPersona() == null) {
                throw new IllegalArgumentException("Debe enviar datosPersona para cliente tipo PERSONA");
            }

            DatosPersona persona = cliente.getDatosPersona();
            if (persona == null) {
                throw new RuntimeException("El cliente no tiene datos de persona asociados");
            }

            persona.setNombres(dto.datosPersona().nombres());
            persona.setApellidos(dto.datosPersona().apellidos());
            persona.setTelefono(dto.datosPersona().telefono());
            datosPersonaRepository.save(persona);

        } else if (cliente.getTipoCliente() == TipoCliente.EMPRESA) {
            if (dto.datosEmpresa() == null) {
                throw new IllegalArgumentException("Debe enviar datosEmpresa para cliente tipo EMPRESA");
            }

            DatosEmpresa empresa = cliente.getDatosEmpresa();
            if (empresa == null) {
                throw new RuntimeException("El cliente no tiene datos de empresa asociados");
            }

            empresa.setRazonSocial(dto.datosEmpresa().razonSocial());
            empresa.setTelefono(dto.datosEmpresa().telefono());
            datosEmpresaRepository.save(empresa);
        }

        Cliente actualizado = repository.save(cliente);

        return toDTO(actualizado);
    }

    @Override
    @Transactional
    public ClienteDTO crear(ClienteCreateDTO dto) {
        Cliente cliente;

        if (dto.tipoCliente() == TipoCliente.PERSONA) {
            if (dto.datosPersona() == null) {
                throw new IllegalArgumentException("Debe enviar datosPersona para cliente tipo PERSONA");
            }

            Boolean existeDni = datosPersonaRepository.existsByDniIgnoreCaseAllIgnoreCase(dto.datosPersona().dni());

            if (existeDni) {
                throw new DniUsadoException("El dni ya esta siendo usado por otro cliente/tecnico");
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

            Boolean existeRuc = datosEmpresaRepository.existsByRucIgnoreCase(dto.datosEmpresa().ruc());

            if (existeRuc) {
                throw new RucUsadoException("El ruc ya ha sido usado por otra empresa");
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

        return toDTO(guardado);
    }

    @Override
    public ClienteDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Cliente no encontrado con id " + id));

        return toDTO(cliente);
    }

    @Override
    public List<ClienteDTO> listarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream()
                .map(c -> toDTO(c))
                .toList();
    }

    private ClienteDTO toDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getTipoCliente(),
                cliente.getActivo(),
                cliente.getDatosPersona() != null
                        ? new DatosPersonaDTO(
                        cliente.getDatosPersona().getNombres(),
                        cliente.getDatosPersona().getApellidos(),
                        cliente.getDatosPersona().getDni(),
                        cliente.getDatosPersona().getTelefono()
                )
                        : null,
                cliente.getDatosEmpresa() != null
                        ? new DatosEmpresaDTO(
                        cliente.getDatosEmpresa().getRazonSocial(),
                        cliente.getDatosEmpresa().getRuc(),
                        cliente.getDatosEmpresa().getTelefono()
                )
                        : null
        );
    }

}

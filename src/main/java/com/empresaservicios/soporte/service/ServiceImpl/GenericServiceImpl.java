package com.empresaservicios.soporte.service.ServiceImpl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresaservicios.soporte.exception.SolicitudNoEncontradaException;
import com.empresaservicios.soporte.exception.UsuarioNoEncontradoException;
import com.empresaservicios.soporte.service.GenericService;

public abstract class GenericServiceImpl<T, ID> implements GenericService<T, ID> {
    protected final JpaRepository<T, ID> repository;
    private final String nombreEntidad;

    public GenericServiceImpl(JpaRepository<T, ID> repository, String nombreEntidad) {
        this.repository = repository;
        this.nombreEntidad = nombreEntidad;
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    return switch (nombreEntidad) {
                        case "Cliente" -> new UsuarioNoEncontradoException("Cliente no encontrado");
                        case "Solicitud" -> new SolicitudNoEncontradaException("Solicitud no encontrada");
                        case "Tecnico" -> new UsuarioNoEncontradoException("Técnico no encontrado");
                        default -> new RuntimeException(nombreEntidad + " no encontrado");
                    };
                });
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public T update(ID id, T entity) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No existe la entidad con ID: " + id);
        }
        return repository.save(entity);
    }

}

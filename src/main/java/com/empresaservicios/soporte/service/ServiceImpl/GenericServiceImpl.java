package com.empresaservicios.soporte.service.ServiceImpl;

import java.util.List;
// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresaservicios.soporte.service.GenericService;

public abstract class GenericServiceImpl<T, ID> implements GenericService<T, ID> {
    protected final JpaRepository<T, ID> repository;
    private final String nombreEntidad;

    public GenericServiceImpl(JpaRepository<T, ID> repository, String nombreEntidad ) {
        this.repository = repository;
        this.nombreEntidad=nombreEntidad;
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    // @Override
    // public Optional<T> findById(ID id) {
    //     return repository.findById(id);
    // }
     @Override
    public T findById(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(nombreEntidad + " no encontrado"));
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

package com.empresaservicios.soporte.service;

import java.util.List;
// import java.util.Optional;

public interface GenericService<T, ID> {
    List<T>findAll();
    // Optional<T>  findById(ID id);
    T findById(ID id);
    T save(T entity);
    T update(ID id, T entity);
   

}
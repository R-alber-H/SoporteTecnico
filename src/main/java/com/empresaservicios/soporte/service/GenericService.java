package com.empresaservicios.soporte.service;

import java.util.List;


public interface GenericService<T, ID> {
    List<T>findAll();
    T findById(ID id);
    T save(T entity);
    T update(ID id, T entity);
}
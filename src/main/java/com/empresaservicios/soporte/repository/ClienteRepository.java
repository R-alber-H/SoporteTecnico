package com.empresaservicios.soporte.repository;

import com.empresaservicios.soporte.utils.enums.Activo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import com.empresaservicios.soporte.entity.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END " + "FROM Cliente c WHERE c.datosPersona.dni = :dni")
    boolean existsByDni(@Param("dni") String dni);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END " + "FROM Cliente c WHERE c.datosEmpresa.ruc = :ruc")
    boolean existsByRuc(@Param("ruc") String ruc);

    boolean existsByActivo(Activo activo);

    boolean existsByDatosPersona_DniAllIgnoreCase(String dni);
}
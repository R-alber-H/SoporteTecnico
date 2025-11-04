package com.empresaservicios.soporte.controller;

import java.util.List;
import java.util.Map;

import com.empresaservicios.soporte.dto.TecnicoUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresaservicios.soporte.entity.Tecnico;
import com.empresaservicios.soporte.service.TecnicoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tecnicos")
@RequiredArgsConstructor
public class TecnicoController {

    private final TecnicoService tecnicoService;

    @GetMapping
    public ResponseEntity<List<Tecnico>> findAll() {
        List<Tecnico> tecnicos = tecnicoService.findAll();
        return ResponseEntity.ok(tecnicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tecnico> findById(@PathVariable Long id) {
        Tecnico tecnico = tecnicoService.findById(id);
        return ResponseEntity.ok(tecnico);
    }

    @PostMapping
    public ResponseEntity<Tecnico> create(@RequestBody Tecnico tecnico) {
        Tecnico saved = tecnicoService.save(tecnico);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PatchMapping("/{id}/activo")
    public ResponseEntity<Tecnico> cambiarActivo(@PathVariable Long id) {
        Tecnico actualizado = tecnicoService.cambiarActivo(id);
        return ResponseEntity.ok(actualizado);
    }

     @PatchMapping("/{id}/actualizarDatos")
    public ResponseEntity<Tecnico> actualizarDatos(@PathVariable Long id,
                                                   @Valid @RequestBody TecnicoUpdateDTO dto) {
        Tecnico datosNuevos = tecnicoService.actualizarDatos(id, dto);
        return ResponseEntity.ok(datosNuevos);
    }
}

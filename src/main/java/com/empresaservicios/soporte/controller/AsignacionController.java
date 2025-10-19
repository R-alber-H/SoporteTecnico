package com.empresaservicios.soporte.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresaservicios.soporte.entity.Asignacion;
import com.empresaservicios.soporte.service.AsignacionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/asignaciones")
@RequiredArgsConstructor
public class AsignacionController {
    private final AsignacionService asignacionService;

    @GetMapping
    public ResponseEntity<List<Asignacion>> findAll() {
        List<Asignacion> asignacion = asignacionService.findAll();
        return ResponseEntity.ok(asignacion);
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<Asignacion> findById(@PathVariable Long id) {
    // return asignacionService.findById(id)
    // .map(ResponseEntity::ok)
    // .orElseThrow(() -> new RuntimeException("asignacion no encontrada"));
    // }
    @GetMapping("/{id}")
    public ResponseEntity<Asignacion> findById(@PathVariable Long id) {
        Asignacion asignacion = asignacionService.findById(id);
        return ResponseEntity.ok(asignacion);
    }

    @PostMapping
    public ResponseEntity<Asignacion> create(@RequestBody Asignacion asignacion) {
        Asignacion saved = asignacionService.save(asignacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asignacion> update(@PathVariable Long id, @RequestBody Asignacion asignacion) {
        Asignacion updated = asignacionService.update(id, asignacion);
        return ResponseEntity.ok(updated);
    }
}

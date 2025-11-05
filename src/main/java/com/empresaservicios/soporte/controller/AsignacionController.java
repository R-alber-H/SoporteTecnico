package com.empresaservicios.soporte.controller;

import java.util.List;

import com.empresaservicios.soporte.dto.AsignacionCreateDTO;
import com.empresaservicios.soporte.dto.AsignacionDTO;
import com.empresaservicios.soporte.dto.AsignacionUpdateDTO;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<AsignacionDTO>> findAll() {
        List<AsignacionDTO> asignacion = asignacionService.listarTodos();
        return ResponseEntity.ok(asignacion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsignacionDTO> findById(@PathVariable Long id) {
        AsignacionDTO asignacion = asignacionService.encontrarPorId(id);
        return ResponseEntity.ok(asignacion);
    }

    @PostMapping
    public ResponseEntity<AsignacionDTO> create(@Valid @RequestBody AsignacionCreateDTO dto) {
        AsignacionDTO saved = asignacionService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsignacionDTO> update(@PathVariable Long id,
                                                @Valid @RequestBody AsignacionUpdateDTO dto) {
        AsignacionDTO updated = asignacionService.actualizar(id, dto);
        return ResponseEntity.ok(updated);
    }
}

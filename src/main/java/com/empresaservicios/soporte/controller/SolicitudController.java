package com.empresaservicios.soporte.controller;

import java.util.List;

import com.empresaservicios.soporte.dto.SolicitudCreateDTO;
import com.empresaservicios.soporte.dto.SolicitudDTO;
import com.empresaservicios.soporte.dto.SolicitudUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresaservicios.soporte.entity.Solicitud;
import com.empresaservicios.soporte.service.SolicitudService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
public class SolicitudController {

    private final SolicitudService solicitudService;

    @GetMapping
    public ResponseEntity<List<SolicitudDTO>> findAll() {
        List<SolicitudDTO> solicitud = solicitudService.buscarTodos();
        return ResponseEntity.ok(solicitud);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudDTO> findById(@PathVariable Long id) {
        SolicitudDTO solicitud = solicitudService.buscarPorId(id);
        return ResponseEntity.ok(solicitud);
    }

    @PostMapping
    public ResponseEntity<SolicitudDTO> create(@Valid @RequestBody SolicitudCreateDTO dto) {
        SolicitudDTO saved = solicitudService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudDTO> update(@PathVariable Long id,
                                               @Valid @RequestBody SolicitudUpdateDTO solicitud) {
        SolicitudDTO updated = solicitudService.actualizar(id, solicitud);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}/activo")
    public ResponseEntity<SolicitudDTO> cambiarActivo(@PathVariable Long id) {
        SolicitudDTO actualizado = solicitudService.cambiarActivo(id);
        return ResponseEntity.ok(actualizado);
    }

    @PatchMapping("/{id}/resuelto")
    public ResponseEntity<SolicitudDTO> marcarResuelto(@PathVariable Long id){
        SolicitudDTO actualizado = solicitudService.marcarResuelto(id);
        return ResponseEntity.ok(actualizado);
    }

    @PatchMapping("/{id}/no-resuelto")
    public ResponseEntity<SolicitudDTO> marcarNoResuelto(@PathVariable Long id){
        SolicitudDTO actualizado = solicitudService.marcarNoResuelto(id);
        return ResponseEntity.ok(actualizado);
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<SolicitudDTO> marcarCancelado(@PathVariable Long id){
        SolicitudDTO actualizado = solicitudService.marcarCancelado(id);
        return ResponseEntity.ok(actualizado);
    }

}

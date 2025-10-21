package com.empresaservicios.soporte.controller;

import java.util.List;

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
    public ResponseEntity<List<Solicitud>> findAll() {
        List<Solicitud> solicitud = solicitudService.findAll();
        return ResponseEntity.ok(solicitud);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> findById(@PathVariable Long id) {
        Solicitud solicitud = solicitudService.findById(id);
        return ResponseEntity.ok(solicitud);
    }

    @PostMapping
    public ResponseEntity<Solicitud> create(@RequestBody Solicitud solicitud) {
        Solicitud saved = solicitudService.save(solicitud);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Solicitud> update(@PathVariable Long id, @RequestBody Solicitud solicitud) {
        Solicitud updated = solicitudService.update(id, solicitud);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}/activo")
    public ResponseEntity<Solicitud> cambiarActivo(@PathVariable Long id) {
        Solicitud actualizado = solicitudService.cambiarActivo(id);
        return ResponseEntity.ok(actualizado);
    }

    @PatchMapping("/{id}/resuelto")
    public ResponseEntity<Solicitud> marcarResuelto(@PathVariable Long id){
        Solicitud actualizado = solicitudService.marcarResuelto(id);
        return ResponseEntity.ok(actualizado);
    }

    @PatchMapping("/{id}/no-resuelto")
    public ResponseEntity<Solicitud> marcarNoResuelto(@PathVariable Long id){
        Solicitud actualizado = solicitudService.marcarNoResuelto(id);
        return ResponseEntity.ok(actualizado);
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Solicitud> marcarCancelado(@PathVariable Long id){
        Solicitud actualizado = solicitudService.marcarCancelado(id);
        return ResponseEntity.ok(actualizado);
    }

}

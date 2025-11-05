package com.empresaservicios.soporte.controller;

import java.util.List;
import java.util.Map;

import com.empresaservicios.soporte.dto.ClienteCreateDTO;
import com.empresaservicios.soporte.dto.ClienteDTO;
import com.empresaservicios.soporte.dto.ClienteUpdateDTO;
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

import com.empresaservicios.soporte.entity.Cliente;
import com.empresaservicios.soporte.service.ClienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<ClienteDTO> clientes = clienteService.listarTodos();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
        ClienteDTO cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody ClienteCreateDTO dto) {
        ClienteDTO saved = clienteService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PatchMapping("/{id}/activo")
    public ResponseEntity<ClienteDTO> cambiarActivo(@PathVariable Long id) {
        ClienteDTO actualizado = clienteService.cambiarActivo(id);
        return ResponseEntity.ok(actualizado);
    }

    @PatchMapping("/{id}/actualizarDatos")
    public ResponseEntity<ClienteDTO> actualizarDatos(@PathVariable Long id,
                                                      @Valid @RequestBody ClienteUpdateDTO dto) {
        ClienteDTO datosNuevos = clienteService.actualizarDatos(id, dto);
        return ResponseEntity.ok(datosNuevos);
    }

}

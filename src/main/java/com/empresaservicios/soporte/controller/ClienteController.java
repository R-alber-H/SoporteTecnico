package com.empresaservicios.soporte.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> clientes = clienteService.findAll();
        return ResponseEntity.ok(clientes);
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<Cliente> findById(@PathVariable Long id) {
    //     return clienteService.findById(id)
    //             .map(ResponseEntity::ok)
    //             .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    // }
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
        Cliente saved = clienteService.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PatchMapping("/{id}/activo")
    public ResponseEntity<Cliente> cambiarActivo(@PathVariable Long id) {
        Cliente actualizado = clienteService.cambiarActivo(id);
        return ResponseEntity.ok(actualizado);
    }


    // @PutMapping("/{id}")
    // public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente) {
    //     Cliente updated = clienteService.update(id, cliente);
    //     return ResponseEntity.ok(updated);
    // }

}

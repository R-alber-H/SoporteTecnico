package com.empresaservicios.soporte.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//TODO: MANEJAR LA EXCEPCTION DEL VALID Y DE CONSTRAINT
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<Map<String,String>> handleUserNotFound(UsuarioNoEncontradoException ex) {
        Map<String,String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(SolicitudNoEncontradaException.class)
    public ResponseEntity<Map<String,String>> handleSolicitudNoEncontrada(SolicitudNoEncontradaException ex) {
        Map<String,String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocurrió un error: " + ex.getMessage());
    }
}

package com.empresaservicios.soporte.exception;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(String mensaje){
        super(mensaje);
    }
}

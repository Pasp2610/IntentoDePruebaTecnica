package com.prueba.demo.exception;

public class ClientNotFound extends RuntimeException {
    public ClientNotFound(String mensaje){
        super(mensaje);
    }
}

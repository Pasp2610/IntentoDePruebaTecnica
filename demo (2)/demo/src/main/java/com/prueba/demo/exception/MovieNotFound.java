package com.prueba.demo.exception;

public class MovieNotFound extends RuntimeException{
    public MovieNotFound(String mensaje){
        super(mensaje);
    }

}

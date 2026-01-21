package com.jupitters.movieApi.exception;

public class EmptyFileException extends RuntimeException {
    public EmptyFileException(String m){
        super(m);
    }
}

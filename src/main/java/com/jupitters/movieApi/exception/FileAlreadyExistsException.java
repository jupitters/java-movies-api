package com.jupitters.movieApi.exception;

public class FileAlreadyExistsException extends RuntimeException {
    public FileAlreadyExistsException(String m){
        super(m);
    }
}

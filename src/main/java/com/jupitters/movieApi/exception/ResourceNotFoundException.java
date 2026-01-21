package com.jupitters.movieApi.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String m){
        super(m);
    }
}

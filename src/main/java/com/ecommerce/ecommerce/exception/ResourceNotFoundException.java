package com.ecommerce.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // Indica que esta excepción debería resultar en un HTTP 404 Not Found
public class ResourceNotFoundException extends RuntimeException {

      // Constructor que acepta un mensaje general
    public ResourceNotFoundException(String message) {
        super(message);
    }

    // Constructor más específico para indicar qué recurso no se encontró y por qué campo
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format( "%s no encontrado con %s : '%s'", resourceName, fieldName, fieldValue));
    }

}

package com.ecommerce.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // Indica que esta excepción debería resultar en un HTTP 409 Conflict
public class DuplicateResourceException extends RuntimeException {


    // Constructor que acepta un mensaje general
    public DuplicateResourceException(String message) {
        super(message);
    }

    // Constructor más específico para indicar qué recurso ya existe y por qué campo
    public DuplicateResourceException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("En el apartado %s ya existe un registro con %s : '%s'", resourceName, fieldName, fieldValue));
    }
    
}

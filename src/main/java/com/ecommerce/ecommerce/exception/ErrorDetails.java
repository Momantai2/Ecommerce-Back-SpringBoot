package com.ecommerce.ecommerce.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
 private LocalDateTime timestamp; // Cuándo ocurrió el error
    private String message;          // Un mensaje descriptivo del error
    private String details;          // Detalles adicionales (ej. la URL de la solicitud)
    private int status;              // El código de estado HTTP (ej. 404, 400, 500)
}

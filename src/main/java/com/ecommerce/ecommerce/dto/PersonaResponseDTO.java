package com.ecommerce.ecommerce.dto;

import java.time.LocalDate;
import java.util.Optional;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class PersonaResponseDTO {
  private Long idPersona;
    private String dni;
    private String primerNombre;
    private String segundoNombre;
    private String tercernombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private Byte idSexo;
    private String telefono;
    private String email;
  public String getNombreCompleto() {
        // Concatena las partes del nombre, manejando los nulos si segundoNombre puede ser null
        return String.join(" ",
            primerNombre,
            Optional.ofNullable(segundoNombre).orElse(""), // Si segundoNombre es null, usa cadena vacía
            Optional.ofNullable(tercernombre).orElse(""), // Si tercernombre es null, usa cadena vacía
            apellidoPaterno,
            apellidoMaterno
        ).trim().replaceAll("\\s+", " "); // Elimina espacios extra
    }
}
package com.ecommerce.ecommerce.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaRequestDTO {
 @NotBlank(message = "El DNI no puede estar vacío")
    @Size(min = 8, max = 8, message = "El DNI debe tener 8 dígitos")
    @Pattern(regexp = "\\d+", message = "El DNI debe contener solo números")
    private String dni;

    @NotBlank(message = "El primer nombre no puede estar vacío")
    @Size(max = 50, message = "El primer nombre no puede exceder los 50 caracteres")
    private String primerNombre;

    @Size(max = 50, message = "El segundo nombre no puede exceder los 50 caracteres")
    private String segundoNombre; // Puede ser nulo o vacío, por eso no @NotBlank

    @Size(max = 50, message = "El tercer nombre no puede exceder los 50 caracteres")
    private String tercernombre;

    @NotBlank(message = "El apellido paterno no puede estar vacío")
    @Size(max = 50, message = "El apellido paterno no puede exceder los 50 caracteres")
    private String apellidoPaterno;

    @NotBlank(message = "El apellido materno no puede estar vacío")
    @Size(max = 50, message = "El apellido materno no puede exceder los 50 caracteres")
    private String apellidoMaterno;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    private LocalDate fechaNacimiento;

    @NotNull(message = "El sexo es obligatorio")
    private Byte idSexo;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Size(min = 7, max = 15, message = "El teléfono debe tener entre 7 y 15 dígitos")
    @Pattern(regexp = "^\\+?\\d+$", message = "El teléfono debe contener solo números y opcionalmente un '+' al inicio")
    private String telefono;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe tener un formato válido")
    @Size(max = 100, message = "El email no puede exceder los 100 caracteres")
    private String email;
}

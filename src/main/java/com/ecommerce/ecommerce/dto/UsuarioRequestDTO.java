package com.ecommerce.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {

    @NotNull(message = "El ID de la persona es obligatorio")
    private Long idPersona;

    @NotBlank(message = "El nombre de usuario no puede estar vacía")
    @Size(min = 6, max = 25, message = "EL nombre de usuario debe tener entre 6 y 25 caracteres")
    private String nombreUsuario;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 6, max = 255, message = "La contraseña debe tener entre 6 y 255 caracteres")
    private String password;

    @NotNull(message = "El ID del rol es obligatorio")
    private Long idRol;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;
}

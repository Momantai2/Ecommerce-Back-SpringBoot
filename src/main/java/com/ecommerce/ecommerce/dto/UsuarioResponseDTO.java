package com.ecommerce.ecommerce.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioResponseDTO {
    private Long idUsuario;
    private Long idPersona;
    private String nombreUsuario;
    private String password;
    private Long idRol;
    private Boolean estado;
}

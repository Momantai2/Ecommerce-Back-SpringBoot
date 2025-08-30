package com.ecommerce.ecommerce.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RolResponseDTO {
    private Long idRol;
    private String nombre;
    private String descripcion;
    private Boolean estado;
}

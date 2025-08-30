package com.ecommerce.ecommerce.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoriaResponseDTO {
    private Long idCategoria;
    private String nombre;
    private String descripcion;
    private Boolean estado;

}

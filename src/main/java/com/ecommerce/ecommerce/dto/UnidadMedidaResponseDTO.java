package com.ecommerce.ecommerce.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UnidadMedidaResponseDTO {
    private Long idUnidadMedida;
    private String nombre;
    private String abreviatura;
    private Boolean estado;
}

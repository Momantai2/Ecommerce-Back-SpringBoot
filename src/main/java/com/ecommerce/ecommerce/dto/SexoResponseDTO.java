package com.ecommerce.ecommerce.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SexoResponseDTO {
    private Byte idSexo;
    private String nombre;
    private Boolean estado;
}

package com.ecommerce.ecommerce.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarritoResponseDTO {
    private Long idCarrito;
    private Long idUsuario;
    private Boolean estado;

    private List<ItemCarritoResponseDTO> items; // importante

}

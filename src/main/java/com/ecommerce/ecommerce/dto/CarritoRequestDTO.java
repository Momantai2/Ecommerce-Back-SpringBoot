package com.ecommerce.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoRequestDTO {

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long idUsuario;

     @NotNull(message = "El estado activo es obligatorio")
    private Boolean estado;
}

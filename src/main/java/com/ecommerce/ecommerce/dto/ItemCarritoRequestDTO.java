package com.ecommerce.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCarritoRequestDTO {

    @NotNull(message = "El ID del carrito es obligatorio")
    private Long idCarrito;

    @NotNull(message = "El ID del producto es obligatorio")
    private Long idProducto;

    private Integer cantidad;

    @NotNull(message = "El estado activo es obligatorio")
    private Boolean estado;
}

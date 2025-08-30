package com.ecommerce.ecommerce.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemCarritoResponseDTO {
    private Long idItemCarrito;
    private Long idCarrito;
    private Long idProducto;
    private Boolean estado;
private Integer cantidad;
   private String nombre; // ✅ necesario para setNombre
    private Double precio; // ✅ necesario para setPrecio
    private String descripcion;
    private String imagenUrl;
    private Integer  stock;

}

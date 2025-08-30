package com.ecommerce.ecommerce.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductoResponseDTO {
    private Long idProducto;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private String imagenUrl;
    private Long idCategoria;
    private Long idUnidadMedida;
    private Integer stock;
    private Boolean estado;
    private String nombreCategoria; 
        private String nombreUnidadMedida; // <-- Agregado


}

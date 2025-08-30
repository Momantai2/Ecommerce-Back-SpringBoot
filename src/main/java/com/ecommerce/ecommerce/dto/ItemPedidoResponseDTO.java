package com.ecommerce.ecommerce.dto;

import lombok.Data;

@Data
public class ItemPedidoResponseDTO {
    private Long idProducto;
    private String nombreProducto;
    private Integer cantidad;
    private Double precioUnitario;
    private String imagenUrl;

    
}
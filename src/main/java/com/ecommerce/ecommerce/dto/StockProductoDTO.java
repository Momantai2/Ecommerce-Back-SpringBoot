package com.ecommerce.ecommerce.dto;

import lombok.Data;

@Data
public class StockProductoDTO {
    private String nombre;
    private int stock;
    public StockProductoDTO(String nombre, int stock) {
        this.nombre = nombre; this.stock = stock;
    }
}

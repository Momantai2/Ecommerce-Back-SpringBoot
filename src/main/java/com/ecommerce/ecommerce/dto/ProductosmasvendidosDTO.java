package com.ecommerce.ecommerce.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductosmasvendidosDTO {
    private String nombre;
    private long cantidadVendida;
}

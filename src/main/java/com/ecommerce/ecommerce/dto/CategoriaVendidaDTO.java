package com.ecommerce.ecommerce.dto;

import lombok.Data;

@Data
public class CategoriaVendidaDTO {
    private String nombre;
    private long cantidadVendida;
    public CategoriaVendidaDTO(String nombre, long cantidadVendida) {
        this.nombre = nombre; this.cantidadVendida = cantidadVendida;
    }
}
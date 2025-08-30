package com.ecommerce.ecommerce.dto;

import lombok.Data;

@Data
public class ItemStripeDTO {
    private String nombre;
    private Double precio; // en dólares
    private Integer cantidad;
}

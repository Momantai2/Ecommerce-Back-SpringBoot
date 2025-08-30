package com.ecommerce.ecommerce.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadMedida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUnidadMedida;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(length = 10)
    private String abreviatura;

    @Column(nullable = false)
    private Boolean estado = true;
}

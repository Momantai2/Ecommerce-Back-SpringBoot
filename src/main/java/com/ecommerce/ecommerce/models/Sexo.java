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
public class Sexo {

    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte idSexo;

    @Column(nullable = false, unique = true, length = 20)
    private String nombre;

     @Column(nullable = false)
    private Boolean estado = true;
}

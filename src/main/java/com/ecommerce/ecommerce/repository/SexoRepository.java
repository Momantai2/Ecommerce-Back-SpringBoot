package com.ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.models.Sexo;

public interface SexoRepository extends JpaRepository<Sexo, Long>{
    
boolean existsByNombre(String nombre);

}

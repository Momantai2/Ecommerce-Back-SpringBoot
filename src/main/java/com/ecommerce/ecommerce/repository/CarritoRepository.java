package com.ecommerce.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.models.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
Optional<Carrito> findByUsuarioIdUsuarioAndEstado(Long idUsuario, Boolean estado);



}

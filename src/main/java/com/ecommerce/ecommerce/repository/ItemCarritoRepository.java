package com.ecommerce.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.models.ItemCarrito;

public interface ItemCarritoRepository extends JpaRepository<ItemCarrito, Long>{
  Optional<ItemCarrito> findByCarritoIdCarritoAndProductoIdProducto(Long idCarrito, Long idProducto);
    List<ItemCarrito> findByCarritoIdCarrito(Long idCarrito);
}

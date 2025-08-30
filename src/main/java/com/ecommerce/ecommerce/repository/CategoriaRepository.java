package com.ecommerce.ecommerce.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.ecommerce.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    boolean existsByNombre(String nombre);

    // FILTRO BUSQUEDA
  Page<Categoria> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);

    // Búsqueda dinámica por nombre y estado (ambos opcionales)
    @Query("SELECT c FROM Categoria c WHERE " +
           "(:nombre IS NULL OR LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) AND " +
           "(:estado IS NULL OR c.estado = :estado)")
    Page<Categoria> buscarPorNombreYEstado(@Param("nombre") String nombre,
                                           @Param("estado") Boolean estado,
                                           Pageable pageable);
                                           
}
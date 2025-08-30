package com.ecommerce.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.models.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
    Optional<Estado> findByNombreIgnoreCase(String nombre);
    
    //filtro para n campo
    Page<Estado> findByNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase(
        String nombre, String descripcion, Pageable pageable);
                boolean existsByNombre(String nombre);

                //filtro para los dos campos
                @Query("SELECT e FROM Estado e " +
       "WHERE (:nombre IS NULL OR LOWER(e.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) " +
       "AND (:descripcion IS NULL OR LOWER(e.descripcion) LIKE LOWER(CONCAT('%', :descripcion, '%')))")
Page<Estado> buscarPorNombreYDescripcion(@Param("nombre") String nombre,
                                         @Param("descripcion") String descripcion,
                                         Pageable pageable);

                

}
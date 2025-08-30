package com.ecommerce.ecommerce.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.ecommerce.models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

    @Query("SELECT p.nombre, p.stock FROM Producto p WHERE p.stock <= :umbral ORDER BY p.stock ASC")
List<Object[]> findLowStockProducts(@Param("umbral") int umbral);

@Query("SELECT c.nombre, SUM(ip.cantidad) FROM ItemPedido ip JOIN ip.producto p JOIN p.categoria c GROUP BY c.nombre")
List<Object[]> findSoldQuantityByCategory();

  boolean existsByNombre(String nombre);

    // FILTRO BUSQUEDA
  Page<Producto> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);

    // Búsqueda dinámica por nombre y estado (ambos opcionales)
    @Query("SELECT c FROM Producto c WHERE " +
           "(:nombre IS NULL OR LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) AND " +
           "(:estado IS NULL OR c.estado = :estado)")
    Page<Producto> buscarPorNombreYEstado(@Param("nombre") String nombre,
                                           @Param("estado") Boolean estado,
                                           Pageable pageable);
                                           
}
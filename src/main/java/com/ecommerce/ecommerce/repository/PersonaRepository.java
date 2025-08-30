package com.ecommerce.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.models.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{
    // Método para buscar una persona por su DNI. Útil para verificar duplicados.
    Optional<Persona> findByDni(String dni);

  
    // Puedes añadir más métodos de búsqueda personalizados si los necesitas en el futuro,
    // por ejemplo, List<Persona> findByPrimerNombreContaining(String nombre);
}

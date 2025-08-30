package com.ecommerce.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.ecommerce.ecommerce.dto.PersonaRequestDTO;
import com.ecommerce.ecommerce.dto.PersonaResponseDTO;
import com.ecommerce.ecommerce.models.Persona;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING) // Indica que Spring gestionará esta interfaz como un componente
public interface PersonaMapper {
 // Método para convertir PersonaRequestDTO a Persona (para crear/actualizar)
    @Mapping(target = "idPersona", ignore = true) // Ignora idPersona al convertir de DTO a Entidad (DB lo genera)
    Persona toEntity(PersonaRequestDTO dto);

    // Método para convertir Persona a PersonaResponseDTO (para enviar al cliente)
    // El getNombreCompleto() en PersonaResponseDTO se encargará de esto automáticamente
    PersonaResponseDTO toDto(Persona entity);

    // Método para actualizar una entidad Persona existente desde un DTO
    // Se usa en el método update del servicio
    @Mapping(target = "idPersona", ignore = true) // No actualices el ID
    void updateEntityFromDto(PersonaRequestDTO dto, @org.mapstruct.MappingTarget Persona entity);

    // Método para convertir una lista de Persona a una lista de PersonaResponseDTO
    List<PersonaResponseDTO> toDtoList(List<Persona> entities);
}

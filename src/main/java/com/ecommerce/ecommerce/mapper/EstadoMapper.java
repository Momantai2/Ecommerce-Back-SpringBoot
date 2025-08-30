package com.ecommerce.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.ecommerce.ecommerce.dto.EstadoRequestDTO;
import com.ecommerce.ecommerce.dto.EstadoResponseDTO;
import com.ecommerce.ecommerce.models.Estado;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EstadoMapper {

    // Convierte Estado -> EstadoResponseDTO
    EstadoResponseDTO toDto(Estado estado);

    // Convierte una lista de Estados -> lista de EstadoResponseDTO
    List<EstadoResponseDTO> toDtoList(List<Estado> estados);

    // Convierte EstadoRequestDTO -> Estado (para crear)
    @Mapping(target = "idEstado", ignore = true)
    Estado toEntity(EstadoRequestDTO dto);

    // Actualiza una entidad existente con los datos del DTO (para update)
    @Mapping(target = "idEstado", ignore = true)
    void updateEntityFromDto(EstadoRequestDTO dto, @MappingTarget Estado estado);
}

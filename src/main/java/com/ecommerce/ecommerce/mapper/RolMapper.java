package com.ecommerce.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.ecommerce.ecommerce.dto.RolRequestDTO;
import com.ecommerce.ecommerce.dto.RolResponseDTO;
import com.ecommerce.ecommerce.models.Rol;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RolMapper {

    @Mapping(target = "idRol", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    Rol toEntity(RolRequestDTO dto);

    RolResponseDTO toDto(Rol entity);

    @Mapping(target = "idRol", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    void updateEntityFromDto(RolRequestDTO dto, @MappingTarget Rol entity);

    List<RolResponseDTO> toDtoList(List<Rol> entities);
}

package com.ecommerce.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.ecommerce.ecommerce.dto.UsuarioRequestDTO;
import com.ecommerce.ecommerce.dto.UsuarioResponseDTO;
import com.ecommerce.ecommerce.models.Usuario;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {

    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "persona", ignore = true) // Será seteado desde el servicio
    @Mapping(target = "rol", ignore = true)  
    Usuario toEntity(UsuarioRequestDTO dto);

    @Mapping(source = "persona.idPersona", target = "idPersona")
    @Mapping(source = "rol.idRol", target = "idRol")
    UsuarioResponseDTO toDto(Usuario entity);

    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "persona", ignore = true) // Será seteado desde el servicio
    @Mapping(target = "rol", ignore = true)  
    void updateEntityFromDto(UsuarioRequestDTO dto, @MappingTarget Usuario entity);

    List<UsuarioResponseDTO> toDtoList(List<Usuario> entities);
}

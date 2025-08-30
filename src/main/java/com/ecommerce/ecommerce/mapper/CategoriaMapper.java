package com.ecommerce.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.ecommerce.ecommerce.dto.CategoriaRequestDTO;
import com.ecommerce.ecommerce.dto.CategoriaResponseDTO;
import com.ecommerce.ecommerce.models.Categoria;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoriaMapper {

    @Mapping(target = "idCategoria", ignore = true)
    Categoria toEntity(CategoriaRequestDTO dto);

    CategoriaResponseDTO toDto(Categoria entity);

    @Mapping(target = "idCategoria", ignore = true)
    void updateEntityFromDto(CategoriaRequestDTO dto, @MappingTarget Categoria entity);

    List<CategoriaResponseDTO> toDtoList(List<Categoria> entities);
}

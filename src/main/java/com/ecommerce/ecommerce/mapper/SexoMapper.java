package com.ecommerce.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.ecommerce.ecommerce.dto.SexoRequestDTO;
import com.ecommerce.ecommerce.dto.SexoResponseDTO;
import com.ecommerce.ecommerce.models.Sexo;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SexoMapper {

    @Mapping(target = "idSexo", ignore = true)
    Sexo toEntity(SexoRequestDTO dto);

    SexoResponseDTO toDto(Sexo entity);

    @Mapping(target = "idSexo", ignore = true)
    void updateEntityFromDto(SexoRequestDTO dto, @MappingTarget Sexo entity);

    List<SexoResponseDTO> toDtoList(List<Sexo> entities);
}

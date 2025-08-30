package com.ecommerce.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.ecommerce.ecommerce.dto.UnidadMedidaRequestDTO;
import com.ecommerce.ecommerce.dto.UnidadMedidaResponseDTO;
import com.ecommerce.ecommerce.models.UnidadMedida;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UnidadMedidaMapper {

    @Mapping(target = "idUnidadMedida", ignore = true)
    UnidadMedida toEntity(UnidadMedidaRequestDTO dto);

  
    UnidadMedidaResponseDTO toDto(UnidadMedida entity);

    @Mapping(target = "idUnidadMedida", ignore = true)
    void updateEntityFromDto(UnidadMedidaRequestDTO dto, @MappingTarget UnidadMedida entity);

    List<UnidadMedidaResponseDTO> toDtoList(List<UnidadMedida> entities);
}

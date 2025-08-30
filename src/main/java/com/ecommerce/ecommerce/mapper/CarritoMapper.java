package com.ecommerce.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.ecommerce.ecommerce.dto.CarritoRequestDTO;
import com.ecommerce.ecommerce.dto.CarritoResponseDTO;
import com.ecommerce.ecommerce.models.Carrito;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = ItemCarritoMapper.class)
public interface CarritoMapper {

    @Mapping(target = "idCarrito", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    Carrito toEntity(CarritoRequestDTO dto);

    @Mapping(source = "usuario.idUsuario", target = "idUsuario")
    CarritoResponseDTO toDto(Carrito entity);

    List<CarritoResponseDTO> toDtoList(List<Carrito> entities);

    @Mapping(target = "idCarrito", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    void updateEntityFromDto(CarritoRequestDTO dto, @MappingTarget Carrito entity);
}

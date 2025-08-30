package com.ecommerce.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.ecommerce.ecommerce.dto.ItemCarritoRequestDTO;
import com.ecommerce.ecommerce.dto.ItemCarritoResponseDTO;
import com.ecommerce.ecommerce.models.ItemCarrito;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemCarritoMapper {

    @Mapping(target = "idItemCarrito", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "carrito", ignore = true)
    @Mapping(target = "producto", ignore = true)
    @Mapping(target = "cantidad", ignore = true)
     @Mapping(target = "estado", ignore = true)
    ItemCarrito toEntity(ItemCarritoRequestDTO dto);

    @Mapping(source = "producto.idProducto", target = "idProducto")
    @Mapping(source = "carrito.idCarrito", target = "idCarrito")
    @Mapping(source = "idItemCarrito", target = "idItemCarrito")
    ItemCarritoResponseDTO toDto(ItemCarrito entity);

    @Mapping(target = "idItemCarrito", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "carrito", ignore = true)
    @Mapping(target = "producto", ignore = true)
      @Mapping(target = "cantidad", ignore = true)
        @Mapping(target = "estado", ignore = true)
    void updateEntityFromDto(ItemCarritoRequestDTO dto, @MappingTarget ItemCarrito entity);

    List<ItemCarritoResponseDTO> toDtoList(List<ItemCarrito> entities);
}

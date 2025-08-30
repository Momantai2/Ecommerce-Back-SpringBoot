package com.ecommerce.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.ecommerce.ecommerce.dto.ItemPedidoResponseDTO;
import com.ecommerce.ecommerce.models.ItemPedido;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemPedidoMapper {

    @Mapping(source = "producto.idProducto", target = "idProducto")
    @Mapping(source = "producto.nombre", target = "nombreProducto")
    @Mapping(source = "producto.imagenUrl", target = "imagenUrl")
    @Mapping(source = "cantidad", target = "cantidad")
    @Mapping(source = "precioUnitario", target = "precioUnitario")
    ItemPedidoResponseDTO toDto(ItemPedido itemPedido);

    List<ItemPedidoResponseDTO> toDtoList(List<ItemPedido> items);
}

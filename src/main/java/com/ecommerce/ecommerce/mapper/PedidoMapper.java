package com.ecommerce.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.ecommerce.ecommerce.dto.PedidoResponseDTO;
import com.ecommerce.ecommerce.models.Pedido;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {ItemPedidoMapper.class, EstadoMapper.class})
public interface PedidoMapper {

    @Mapping(source = "usuario.idUsuario", target = "idUsuario")
    @Mapping(source = "usuario.nombreUsuario", target = "nombreUsuario")
    @Mapping(source = "estado", target = "estado") // ← esto lo hace explícito
    PedidoResponseDTO toDto(Pedido pedido);

    List<PedidoResponseDTO> toDtoList(List<Pedido> pedidos);
}

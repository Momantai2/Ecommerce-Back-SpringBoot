package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.PedidoResponseDTO;
import com.ecommerce.ecommerce.models.Pedido;
import com.ecommerce.ecommerce.models.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-30T13:32:13-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class PedidoMapperImpl implements PedidoMapper {

    @Autowired
    private ItemPedidoMapper itemPedidoMapper;
    @Autowired
    private EstadoMapper estadoMapper;

    @Override
    public PedidoResponseDTO toDto(Pedido pedido) {
        if ( pedido == null ) {
            return null;
        }

        PedidoResponseDTO pedidoResponseDTO = new PedidoResponseDTO();

        pedidoResponseDTO.setIdUsuario( pedidoUsuarioIdUsuario( pedido ) );
        pedidoResponseDTO.setNombreUsuario( pedidoUsuarioNombreUsuario( pedido ) );
        pedidoResponseDTO.setEstado( estadoMapper.toDto( pedido.getEstado() ) );
        pedidoResponseDTO.setFecha( pedido.getFecha() );
        pedidoResponseDTO.setIdPedido( pedido.getIdPedido() );
        pedidoResponseDTO.setItems( itemPedidoMapper.toDtoList( pedido.getItems() ) );
        pedidoResponseDTO.setTotal( pedido.getTotal() );

        return pedidoResponseDTO;
    }

    @Override
    public List<PedidoResponseDTO> toDtoList(List<Pedido> pedidos) {
        if ( pedidos == null ) {
            return null;
        }

        List<PedidoResponseDTO> list = new ArrayList<PedidoResponseDTO>( pedidos.size() );
        for ( Pedido pedido : pedidos ) {
            list.add( toDto( pedido ) );
        }

        return list;
    }

    private Long pedidoUsuarioIdUsuario(Pedido pedido) {
        if ( pedido == null ) {
            return null;
        }
        Usuario usuario = pedido.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        Long idUsuario = usuario.getIdUsuario();
        if ( idUsuario == null ) {
            return null;
        }
        return idUsuario;
    }

    private String pedidoUsuarioNombreUsuario(Pedido pedido) {
        if ( pedido == null ) {
            return null;
        }
        Usuario usuario = pedido.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        String nombreUsuario = usuario.getNombreUsuario();
        if ( nombreUsuario == null ) {
            return null;
        }
        return nombreUsuario;
    }
}

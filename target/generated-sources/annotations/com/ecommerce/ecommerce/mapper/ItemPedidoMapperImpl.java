package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.ItemPedidoResponseDTO;
import com.ecommerce.ecommerce.models.ItemPedido;
import com.ecommerce.ecommerce.models.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-30T13:32:13-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class ItemPedidoMapperImpl implements ItemPedidoMapper {

    @Override
    public ItemPedidoResponseDTO toDto(ItemPedido itemPedido) {
        if ( itemPedido == null ) {
            return null;
        }

        ItemPedidoResponseDTO itemPedidoResponseDTO = new ItemPedidoResponseDTO();

        itemPedidoResponseDTO.setIdProducto( itemPedidoProductoIdProducto( itemPedido ) );
        itemPedidoResponseDTO.setNombreProducto( itemPedidoProductoNombre( itemPedido ) );
        itemPedidoResponseDTO.setImagenUrl( itemPedidoProductoImagenUrl( itemPedido ) );
        itemPedidoResponseDTO.setCantidad( itemPedido.getCantidad() );
        itemPedidoResponseDTO.setPrecioUnitario( itemPedido.getPrecioUnitario() );

        return itemPedidoResponseDTO;
    }

    @Override
    public List<ItemPedidoResponseDTO> toDtoList(List<ItemPedido> items) {
        if ( items == null ) {
            return null;
        }

        List<ItemPedidoResponseDTO> list = new ArrayList<ItemPedidoResponseDTO>( items.size() );
        for ( ItemPedido itemPedido : items ) {
            list.add( toDto( itemPedido ) );
        }

        return list;
    }

    private Long itemPedidoProductoIdProducto(ItemPedido itemPedido) {
        if ( itemPedido == null ) {
            return null;
        }
        Producto producto = itemPedido.getProducto();
        if ( producto == null ) {
            return null;
        }
        Long idProducto = producto.getIdProducto();
        if ( idProducto == null ) {
            return null;
        }
        return idProducto;
    }

    private String itemPedidoProductoNombre(ItemPedido itemPedido) {
        if ( itemPedido == null ) {
            return null;
        }
        Producto producto = itemPedido.getProducto();
        if ( producto == null ) {
            return null;
        }
        String nombre = producto.getNombre();
        if ( nombre == null ) {
            return null;
        }
        return nombre;
    }

    private String itemPedidoProductoImagenUrl(ItemPedido itemPedido) {
        if ( itemPedido == null ) {
            return null;
        }
        Producto producto = itemPedido.getProducto();
        if ( producto == null ) {
            return null;
        }
        String imagenUrl = producto.getImagenUrl();
        if ( imagenUrl == null ) {
            return null;
        }
        return imagenUrl;
    }
}

package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.ItemCarritoRequestDTO;
import com.ecommerce.ecommerce.dto.ItemCarritoResponseDTO;
import com.ecommerce.ecommerce.models.Carrito;
import com.ecommerce.ecommerce.models.ItemCarrito;
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
public class ItemCarritoMapperImpl implements ItemCarritoMapper {

    @Override
    public ItemCarrito toEntity(ItemCarritoRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ItemCarrito itemCarrito = new ItemCarrito();

        return itemCarrito;
    }

    @Override
    public ItemCarritoResponseDTO toDto(ItemCarrito entity) {
        if ( entity == null ) {
            return null;
        }

        ItemCarritoResponseDTO itemCarritoResponseDTO = new ItemCarritoResponseDTO();

        itemCarritoResponseDTO.setIdProducto( entityProductoIdProducto( entity ) );
        itemCarritoResponseDTO.setIdCarrito( entityCarritoIdCarrito( entity ) );
        itemCarritoResponseDTO.setIdItemCarrito( entity.getIdItemCarrito() );
        itemCarritoResponseDTO.setCantidad( entity.getCantidad() );
        itemCarritoResponseDTO.setEstado( entity.getEstado() );

        return itemCarritoResponseDTO;
    }

    @Override
    public void updateEntityFromDto(ItemCarritoRequestDTO dto, ItemCarrito entity) {
        if ( dto == null ) {
            return;
        }
    }

    @Override
    public List<ItemCarritoResponseDTO> toDtoList(List<ItemCarrito> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ItemCarritoResponseDTO> list = new ArrayList<ItemCarritoResponseDTO>( entities.size() );
        for ( ItemCarrito itemCarrito : entities ) {
            list.add( toDto( itemCarrito ) );
        }

        return list;
    }

    private Long entityProductoIdProducto(ItemCarrito itemCarrito) {
        if ( itemCarrito == null ) {
            return null;
        }
        Producto producto = itemCarrito.getProducto();
        if ( producto == null ) {
            return null;
        }
        Long idProducto = producto.getIdProducto();
        if ( idProducto == null ) {
            return null;
        }
        return idProducto;
    }

    private Long entityCarritoIdCarrito(ItemCarrito itemCarrito) {
        if ( itemCarrito == null ) {
            return null;
        }
        Carrito carrito = itemCarrito.getCarrito();
        if ( carrito == null ) {
            return null;
        }
        Long idCarrito = carrito.getIdCarrito();
        if ( idCarrito == null ) {
            return null;
        }
        return idCarrito;
    }
}

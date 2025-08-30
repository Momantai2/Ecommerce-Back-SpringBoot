package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.CarritoRequestDTO;
import com.ecommerce.ecommerce.dto.CarritoResponseDTO;
import com.ecommerce.ecommerce.models.Carrito;
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
public class CarritoMapperImpl implements CarritoMapper {

    @Autowired
    private ItemCarritoMapper itemCarritoMapper;

    @Override
    public Carrito toEntity(CarritoRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Carrito carrito = new Carrito();

        carrito.setEstado( dto.getEstado() );

        return carrito;
    }

    @Override
    public CarritoResponseDTO toDto(Carrito entity) {
        if ( entity == null ) {
            return null;
        }

        CarritoResponseDTO carritoResponseDTO = new CarritoResponseDTO();

        carritoResponseDTO.setIdUsuario( entityUsuarioIdUsuario( entity ) );
        carritoResponseDTO.setEstado( entity.getEstado() );
        carritoResponseDTO.setIdCarrito( entity.getIdCarrito() );
        carritoResponseDTO.setItems( itemCarritoMapper.toDtoList( entity.getItems() ) );

        return carritoResponseDTO;
    }

    @Override
    public List<CarritoResponseDTO> toDtoList(List<Carrito> entities) {
        if ( entities == null ) {
            return null;
        }

        List<CarritoResponseDTO> list = new ArrayList<CarritoResponseDTO>( entities.size() );
        for ( Carrito carrito : entities ) {
            list.add( toDto( carrito ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(CarritoRequestDTO dto, Carrito entity) {
        if ( dto == null ) {
            return;
        }

        entity.setEstado( dto.getEstado() );
    }

    private Long entityUsuarioIdUsuario(Carrito carrito) {
        if ( carrito == null ) {
            return null;
        }
        Usuario usuario = carrito.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        Long idUsuario = usuario.getIdUsuario();
        if ( idUsuario == null ) {
            return null;
        }
        return idUsuario;
    }
}

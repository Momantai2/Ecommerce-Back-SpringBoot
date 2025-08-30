package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.ProductoRequestDTO;
import com.ecommerce.ecommerce.dto.ProductoResponseDTO;
import com.ecommerce.ecommerce.models.Categoria;
import com.ecommerce.ecommerce.models.Producto;
import com.ecommerce.ecommerce.models.UnidadMedida;
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
public class ProductoMapperImpl implements ProductoMapper {

    @Override
    public Producto toEntity(ProductoRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Producto producto = new Producto();

        producto.setDescripcion( dto.getDescripcion() );
        producto.setEstado( dto.getEstado() );
        producto.setImagenUrl( dto.getImagenUrl() );
        producto.setNombre( dto.getNombre() );
        producto.setPrecio( dto.getPrecio() );
        producto.setStock( dto.getStock() );

        return producto;
    }

    @Override
    public ProductoResponseDTO toDto(Producto entity) {
        if ( entity == null ) {
            return null;
        }

        ProductoResponseDTO productoResponseDTO = new ProductoResponseDTO();

        productoResponseDTO.setIdCategoria( entityCategoriaIdCategoria( entity ) );
        productoResponseDTO.setIdUnidadMedida( entityUnidadMedidaIdUnidadMedida( entity ) );
        productoResponseDTO.setNombreUnidadMedida( entityUnidadMedidaNombre( entity ) );
        productoResponseDTO.setNombreCategoria( entityCategoriaNombre( entity ) );
        productoResponseDTO.setDescripcion( entity.getDescripcion() );
        productoResponseDTO.setEstado( entity.getEstado() );
        productoResponseDTO.setIdProducto( entity.getIdProducto() );
        productoResponseDTO.setImagenUrl( entity.getImagenUrl() );
        productoResponseDTO.setNombre( entity.getNombre() );
        productoResponseDTO.setPrecio( entity.getPrecio() );
        productoResponseDTO.setStock( entity.getStock() );

        return productoResponseDTO;
    }

    @Override
    public void updateEntityFromDto(ProductoRequestDTO dto, Producto entity) {
        if ( dto == null ) {
            return;
        }

        entity.setDescripcion( dto.getDescripcion() );
        entity.setEstado( dto.getEstado() );
        entity.setImagenUrl( dto.getImagenUrl() );
        entity.setNombre( dto.getNombre() );
        entity.setPrecio( dto.getPrecio() );
        entity.setStock( dto.getStock() );
    }

    @Override
    public List<ProductoResponseDTO> toDtoList(List<Producto> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ProductoResponseDTO> list = new ArrayList<ProductoResponseDTO>( entities.size() );
        for ( Producto producto : entities ) {
            list.add( toDto( producto ) );
        }

        return list;
    }

    private Long entityCategoriaIdCategoria(Producto producto) {
        if ( producto == null ) {
            return null;
        }
        Categoria categoria = producto.getCategoria();
        if ( categoria == null ) {
            return null;
        }
        Long idCategoria = categoria.getIdCategoria();
        if ( idCategoria == null ) {
            return null;
        }
        return idCategoria;
    }

    private Long entityUnidadMedidaIdUnidadMedida(Producto producto) {
        if ( producto == null ) {
            return null;
        }
        UnidadMedida unidadMedida = producto.getUnidadMedida();
        if ( unidadMedida == null ) {
            return null;
        }
        Long idUnidadMedida = unidadMedida.getIdUnidadMedida();
        if ( idUnidadMedida == null ) {
            return null;
        }
        return idUnidadMedida;
    }

    private String entityUnidadMedidaNombre(Producto producto) {
        if ( producto == null ) {
            return null;
        }
        UnidadMedida unidadMedida = producto.getUnidadMedida();
        if ( unidadMedida == null ) {
            return null;
        }
        String nombre = unidadMedida.getNombre();
        if ( nombre == null ) {
            return null;
        }
        return nombre;
    }

    private String entityCategoriaNombre(Producto producto) {
        if ( producto == null ) {
            return null;
        }
        Categoria categoria = producto.getCategoria();
        if ( categoria == null ) {
            return null;
        }
        String nombre = categoria.getNombre();
        if ( nombre == null ) {
            return null;
        }
        return nombre;
    }
}

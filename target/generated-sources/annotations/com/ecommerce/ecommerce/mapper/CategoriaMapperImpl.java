package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.CategoriaRequestDTO;
import com.ecommerce.ecommerce.dto.CategoriaResponseDTO;
import com.ecommerce.ecommerce.models.Categoria;
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
public class CategoriaMapperImpl implements CategoriaMapper {

    @Override
    public Categoria toEntity(CategoriaRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Categoria categoria = new Categoria();

        categoria.setDescripcion( dto.getDescripcion() );
        categoria.setEstado( dto.getEstado() );
        categoria.setNombre( dto.getNombre() );

        return categoria;
    }

    @Override
    public CategoriaResponseDTO toDto(Categoria entity) {
        if ( entity == null ) {
            return null;
        }

        CategoriaResponseDTO categoriaResponseDTO = new CategoriaResponseDTO();

        categoriaResponseDTO.setDescripcion( entity.getDescripcion() );
        categoriaResponseDTO.setEstado( entity.getEstado() );
        categoriaResponseDTO.setIdCategoria( entity.getIdCategoria() );
        categoriaResponseDTO.setNombre( entity.getNombre() );

        return categoriaResponseDTO;
    }

    @Override
    public void updateEntityFromDto(CategoriaRequestDTO dto, Categoria entity) {
        if ( dto == null ) {
            return;
        }

        entity.setDescripcion( dto.getDescripcion() );
        entity.setEstado( dto.getEstado() );
        entity.setNombre( dto.getNombre() );
    }

    @Override
    public List<CategoriaResponseDTO> toDtoList(List<Categoria> entities) {
        if ( entities == null ) {
            return null;
        }

        List<CategoriaResponseDTO> list = new ArrayList<CategoriaResponseDTO>( entities.size() );
        for ( Categoria categoria : entities ) {
            list.add( toDto( categoria ) );
        }

        return list;
    }
}

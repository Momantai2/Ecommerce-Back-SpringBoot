package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.UnidadMedidaRequestDTO;
import com.ecommerce.ecommerce.dto.UnidadMedidaResponseDTO;
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
public class UnidadMedidaMapperImpl implements UnidadMedidaMapper {

    @Override
    public UnidadMedida toEntity(UnidadMedidaRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        UnidadMedida unidadMedida = new UnidadMedida();

        unidadMedida.setAbreviatura( dto.getAbreviatura() );
        unidadMedida.setEstado( dto.getEstado() );
        unidadMedida.setNombre( dto.getNombre() );

        return unidadMedida;
    }

    @Override
    public UnidadMedidaResponseDTO toDto(UnidadMedida entity) {
        if ( entity == null ) {
            return null;
        }

        UnidadMedidaResponseDTO unidadMedidaResponseDTO = new UnidadMedidaResponseDTO();

        unidadMedidaResponseDTO.setAbreviatura( entity.getAbreviatura() );
        unidadMedidaResponseDTO.setEstado( entity.getEstado() );
        unidadMedidaResponseDTO.setIdUnidadMedida( entity.getIdUnidadMedida() );
        unidadMedidaResponseDTO.setNombre( entity.getNombre() );

        return unidadMedidaResponseDTO;
    }

    @Override
    public void updateEntityFromDto(UnidadMedidaRequestDTO dto, UnidadMedida entity) {
        if ( dto == null ) {
            return;
        }

        entity.setAbreviatura( dto.getAbreviatura() );
        entity.setEstado( dto.getEstado() );
        entity.setNombre( dto.getNombre() );
    }

    @Override
    public List<UnidadMedidaResponseDTO> toDtoList(List<UnidadMedida> entities) {
        if ( entities == null ) {
            return null;
        }

        List<UnidadMedidaResponseDTO> list = new ArrayList<UnidadMedidaResponseDTO>( entities.size() );
        for ( UnidadMedida unidadMedida : entities ) {
            list.add( toDto( unidadMedida ) );
        }

        return list;
    }
}

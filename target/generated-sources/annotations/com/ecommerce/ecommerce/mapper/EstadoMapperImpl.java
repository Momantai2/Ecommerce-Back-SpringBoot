package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.EstadoRequestDTO;
import com.ecommerce.ecommerce.dto.EstadoResponseDTO;
import com.ecommerce.ecommerce.models.Estado;
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
public class EstadoMapperImpl implements EstadoMapper {

    @Override
    public EstadoResponseDTO toDto(Estado estado) {
        if ( estado == null ) {
            return null;
        }

        EstadoResponseDTO estadoResponseDTO = new EstadoResponseDTO();

        estadoResponseDTO.setDescripcion( estado.getDescripcion() );
        estadoResponseDTO.setIdEstado( estado.getIdEstado() );
        estadoResponseDTO.setNombre( estado.getNombre() );

        return estadoResponseDTO;
    }

    @Override
    public List<EstadoResponseDTO> toDtoList(List<Estado> estados) {
        if ( estados == null ) {
            return null;
        }

        List<EstadoResponseDTO> list = new ArrayList<EstadoResponseDTO>( estados.size() );
        for ( Estado estado : estados ) {
            list.add( toDto( estado ) );
        }

        return list;
    }

    @Override
    public Estado toEntity(EstadoRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Estado estado = new Estado();

        estado.setDescripcion( dto.getDescripcion() );
        estado.setNombre( dto.getNombre() );

        return estado;
    }

    @Override
    public void updateEntityFromDto(EstadoRequestDTO dto, Estado estado) {
        if ( dto == null ) {
            return;
        }

        estado.setDescripcion( dto.getDescripcion() );
        estado.setNombre( dto.getNombre() );
    }
}

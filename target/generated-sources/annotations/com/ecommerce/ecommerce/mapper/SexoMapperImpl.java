package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.SexoRequestDTO;
import com.ecommerce.ecommerce.dto.SexoResponseDTO;
import com.ecommerce.ecommerce.models.Sexo;
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
public class SexoMapperImpl implements SexoMapper {

    @Override
    public Sexo toEntity(SexoRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Sexo sexo = new Sexo();

        sexo.setEstado( dto.getEstado() );
        sexo.setNombre( dto.getNombre() );

        return sexo;
    }

    @Override
    public SexoResponseDTO toDto(Sexo entity) {
        if ( entity == null ) {
            return null;
        }

        SexoResponseDTO sexoResponseDTO = new SexoResponseDTO();

        sexoResponseDTO.setEstado( entity.getEstado() );
        sexoResponseDTO.setIdSexo( entity.getIdSexo() );
        sexoResponseDTO.setNombre( entity.getNombre() );

        return sexoResponseDTO;
    }

    @Override
    public void updateEntityFromDto(SexoRequestDTO dto, Sexo entity) {
        if ( dto == null ) {
            return;
        }

        entity.setEstado( dto.getEstado() );
        entity.setNombre( dto.getNombre() );
    }

    @Override
    public List<SexoResponseDTO> toDtoList(List<Sexo> entities) {
        if ( entities == null ) {
            return null;
        }

        List<SexoResponseDTO> list = new ArrayList<SexoResponseDTO>( entities.size() );
        for ( Sexo sexo : entities ) {
            list.add( toDto( sexo ) );
        }

        return list;
    }
}

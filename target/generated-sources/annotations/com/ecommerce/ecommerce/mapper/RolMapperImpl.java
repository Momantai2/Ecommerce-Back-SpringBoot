package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.RolRequestDTO;
import com.ecommerce.ecommerce.dto.RolResponseDTO;
import com.ecommerce.ecommerce.models.Rol;
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
public class RolMapperImpl implements RolMapper {

    @Override
    public Rol toEntity(RolRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Rol rol = new Rol();

        rol.setDescripcion( dto.getDescripcion() );
        rol.setEstado( dto.getEstado() );
        rol.setNombre( dto.getNombre() );

        return rol;
    }

    @Override
    public RolResponseDTO toDto(Rol entity) {
        if ( entity == null ) {
            return null;
        }

        RolResponseDTO rolResponseDTO = new RolResponseDTO();

        rolResponseDTO.setDescripcion( entity.getDescripcion() );
        rolResponseDTO.setEstado( entity.getEstado() );
        rolResponseDTO.setIdRol( entity.getIdRol() );
        rolResponseDTO.setNombre( entity.getNombre() );

        return rolResponseDTO;
    }

    @Override
    public void updateEntityFromDto(RolRequestDTO dto, Rol entity) {
        if ( dto == null ) {
            return;
        }

        entity.setDescripcion( dto.getDescripcion() );
        entity.setEstado( dto.getEstado() );
        entity.setNombre( dto.getNombre() );
    }

    @Override
    public List<RolResponseDTO> toDtoList(List<Rol> entities) {
        if ( entities == null ) {
            return null;
        }

        List<RolResponseDTO> list = new ArrayList<RolResponseDTO>( entities.size() );
        for ( Rol rol : entities ) {
            list.add( toDto( rol ) );
        }

        return list;
    }
}

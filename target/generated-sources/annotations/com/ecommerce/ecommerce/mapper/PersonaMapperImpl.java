package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.PersonaRequestDTO;
import com.ecommerce.ecommerce.dto.PersonaResponseDTO;
import com.ecommerce.ecommerce.models.Persona;
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
public class PersonaMapperImpl implements PersonaMapper {

    @Override
    public Persona toEntity(PersonaRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Persona persona = new Persona();

        persona.setApellidoMaterno( dto.getApellidoMaterno() );
        persona.setApellidoPaterno( dto.getApellidoPaterno() );
        persona.setDni( dto.getDni() );
        persona.setEmail( dto.getEmail() );
        persona.setFechaNacimiento( dto.getFechaNacimiento() );
        persona.setIdSexo( dto.getIdSexo() );
        persona.setPrimerNombre( dto.getPrimerNombre() );
        persona.setSegundoNombre( dto.getSegundoNombre() );
        persona.setTelefono( dto.getTelefono() );
        persona.setTercernombre( dto.getTercernombre() );

        return persona;
    }

    @Override
    public PersonaResponseDTO toDto(Persona entity) {
        if ( entity == null ) {
            return null;
        }

        PersonaResponseDTO personaResponseDTO = new PersonaResponseDTO();

        personaResponseDTO.setApellidoMaterno( entity.getApellidoMaterno() );
        personaResponseDTO.setApellidoPaterno( entity.getApellidoPaterno() );
        personaResponseDTO.setDni( entity.getDni() );
        personaResponseDTO.setEmail( entity.getEmail() );
        personaResponseDTO.setFechaNacimiento( entity.getFechaNacimiento() );
        personaResponseDTO.setIdPersona( entity.getIdPersona() );
        personaResponseDTO.setIdSexo( entity.getIdSexo() );
        personaResponseDTO.setPrimerNombre( entity.getPrimerNombre() );
        personaResponseDTO.setSegundoNombre( entity.getSegundoNombre() );
        personaResponseDTO.setTelefono( entity.getTelefono() );
        personaResponseDTO.setTercernombre( entity.getTercernombre() );

        return personaResponseDTO;
    }

    @Override
    public void updateEntityFromDto(PersonaRequestDTO dto, Persona entity) {
        if ( dto == null ) {
            return;
        }

        entity.setApellidoMaterno( dto.getApellidoMaterno() );
        entity.setApellidoPaterno( dto.getApellidoPaterno() );
        entity.setDni( dto.getDni() );
        entity.setEmail( dto.getEmail() );
        entity.setFechaNacimiento( dto.getFechaNacimiento() );
        entity.setIdSexo( dto.getIdSexo() );
        entity.setPrimerNombre( dto.getPrimerNombre() );
        entity.setSegundoNombre( dto.getSegundoNombre() );
        entity.setTelefono( dto.getTelefono() );
        entity.setTercernombre( dto.getTercernombre() );
    }

    @Override
    public List<PersonaResponseDTO> toDtoList(List<Persona> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PersonaResponseDTO> list = new ArrayList<PersonaResponseDTO>( entities.size() );
        for ( Persona persona : entities ) {
            list.add( toDto( persona ) );
        }

        return list;
    }
}

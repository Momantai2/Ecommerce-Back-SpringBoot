package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.UsuarioRequestDTO;
import com.ecommerce.ecommerce.dto.UsuarioResponseDTO;
import com.ecommerce.ecommerce.models.Persona;
import com.ecommerce.ecommerce.models.Rol;
import com.ecommerce.ecommerce.models.Usuario;
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
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toEntity(UsuarioRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setEstado( dto.getEstado() );
        usuario.setNombreUsuario( dto.getNombreUsuario() );
        usuario.setPassword( dto.getPassword() );

        return usuario;
    }

    @Override
    public UsuarioResponseDTO toDto(Usuario entity) {
        if ( entity == null ) {
            return null;
        }

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();

        usuarioResponseDTO.setIdPersona( entityPersonaIdPersona( entity ) );
        usuarioResponseDTO.setIdRol( entityRolIdRol( entity ) );
        usuarioResponseDTO.setEstado( entity.getEstado() );
        usuarioResponseDTO.setIdUsuario( entity.getIdUsuario() );
        usuarioResponseDTO.setNombreUsuario( entity.getNombreUsuario() );
        usuarioResponseDTO.setPassword( entity.getPassword() );

        return usuarioResponseDTO;
    }

    @Override
    public void updateEntityFromDto(UsuarioRequestDTO dto, Usuario entity) {
        if ( dto == null ) {
            return;
        }

        entity.setEstado( dto.getEstado() );
        entity.setNombreUsuario( dto.getNombreUsuario() );
        entity.setPassword( dto.getPassword() );
    }

    @Override
    public List<UsuarioResponseDTO> toDtoList(List<Usuario> entities) {
        if ( entities == null ) {
            return null;
        }

        List<UsuarioResponseDTO> list = new ArrayList<UsuarioResponseDTO>( entities.size() );
        for ( Usuario usuario : entities ) {
            list.add( toDto( usuario ) );
        }

        return list;
    }

    private Long entityPersonaIdPersona(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }
        Persona persona = usuario.getPersona();
        if ( persona == null ) {
            return null;
        }
        Long idPersona = persona.getIdPersona();
        if ( idPersona == null ) {
            return null;
        }
        return idPersona;
    }

    private Long entityRolIdRol(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }
        Rol rol = usuario.getRol();
        if ( rol == null ) {
            return null;
        }
        Long idRol = rol.getIdRol();
        if ( idRol == null ) {
            return null;
        }
        return idRol;
    }
}

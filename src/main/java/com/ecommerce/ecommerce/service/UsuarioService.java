package com.ecommerce.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.ecommerce.dto.UsuarioRequestDTO;
import com.ecommerce.ecommerce.dto.UsuarioResponseDTO;
import com.ecommerce.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.ecommerce.mapper.UsuarioMapper;
import com.ecommerce.ecommerce.models.Persona;
import com.ecommerce.ecommerce.models.Rol;
import com.ecommerce.ecommerce.models.Usuario;
import com.ecommerce.ecommerce.repository.PersonaRepository;
import com.ecommerce.ecommerce.repository.RolRepository;
import com.ecommerce.ecommerce.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

      private final PersonaRepository personaRepository;
    private final RolRepository rolRepository;


    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> getAllUsuarios() {
        return usuarioMapper.toDtoList(usuarioRepository.findAll());
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO getUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario", "ID", id));
        return usuarioMapper.toDto(usuario);
    }

    @Transactional
    public UsuarioResponseDTO createUsuario(UsuarioRequestDTO dto) {
    
            Persona persona = personaRepository.findById(dto.getIdPersona())
            .orElseThrow(() -> new ResourceNotFoundException("Persona", "ID", dto.getIdPersona()));

        Rol rol = rolRepository.findById(dto.getIdRol())
            .orElseThrow(() -> new ResourceNotFoundException("Rol", "ID", dto.getIdRol()));

        Usuario entity = usuarioMapper.toEntity(dto);
        entity.setPersona(persona);
        entity.setRol(rol);
        Usuario saved = usuarioRepository.save(entity);
        return usuarioMapper.toDto(saved);
    }

    @Transactional
    public UsuarioResponseDTO updateUsuario(Long id, UsuarioRequestDTO dto) {
          Usuario existing = usuarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario", "ID", id));

        Persona persona = personaRepository.findById(dto.getIdPersona())
            .orElseThrow(() -> new ResourceNotFoundException("Persona", "ID", dto.getIdPersona()));

        Rol rol = rolRepository.findById(dto.getIdRol())
            .orElseThrow(() -> new ResourceNotFoundException("Rol", "ID", dto.getIdRol()));
            
 existing.setPersona(persona);
        existing.setRol(rol);
        usuarioMapper.updateEntityFromDto(dto, existing);
        return usuarioMapper.toDto(usuarioRepository.save(existing));
    }

    @Transactional
    public void deleteUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario", "ID", id);
        }
        usuarioRepository.deleteById(id);
    }
}

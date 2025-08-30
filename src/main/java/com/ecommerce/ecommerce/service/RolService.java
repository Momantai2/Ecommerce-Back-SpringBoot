package com.ecommerce.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.ecommerce.dto.RolRequestDTO;
import com.ecommerce.ecommerce.dto.RolResponseDTO;
import com.ecommerce.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.ecommerce.mapper.RolMapper;
import com.ecommerce.ecommerce.models.Rol;
import com.ecommerce.ecommerce.repository.RolRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RolService {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;

    @Transactional(readOnly = true)
    public List<RolResponseDTO> getAll() {
        List<Rol> list = rolRepository.findAll();
        return rolMapper.toDtoList(list);
    }

    @Transactional(readOnly = true)
    public RolResponseDTO getById(Long id) {
        Rol entity = rolRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Rol", "ID", id));
        return rolMapper.toDto(entity);
    }

    @Transactional
    public RolResponseDTO create(RolRequestDTO dto) {
        Rol entity = rolMapper.toEntity(dto);
        Rol saved = rolRepository.save(entity);
        return rolMapper.toDto(saved);
    }

    @Transactional
    public RolResponseDTO update(Long id, RolRequestDTO dto) {
        Rol existing = rolRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Rol", "ID", id));

        rolMapper.updateEntityFromDto(dto, existing);
        Rol updated = rolRepository.save(existing);
        return rolMapper.toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!rolRepository.existsById(id)) {
            throw new ResourceNotFoundException("Rol", "ID", id);
        }
        rolRepository.deleteById(id);
    }
}

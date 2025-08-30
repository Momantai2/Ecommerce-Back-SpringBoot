package com.ecommerce.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.ecommerce.dto.SexoRequestDTO;
import com.ecommerce.ecommerce.dto.SexoResponseDTO;
import com.ecommerce.ecommerce.exception.DuplicateResourceException;
import com.ecommerce.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.ecommerce.mapper.SexoMapper;
import com.ecommerce.ecommerce.models.Sexo;
import com.ecommerce.ecommerce.repository.SexoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SexoService {

    private final SexoRepository sexoRepository;
    private final SexoMapper sexoMapper;

    @Transactional(readOnly = true)
    public List<SexoResponseDTO> getAll() {
        List<Sexo> list = sexoRepository.findAll();
        return sexoMapper.toDtoList(list);
    }

    @Transactional(readOnly = true)
    public SexoResponseDTO getById(Long id) {
        Sexo entity = sexoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Sexo", "ID", id));
        return sexoMapper.toDto(entity);
    }

    @Transactional
    public SexoResponseDTO create(SexoRequestDTO dto) {

          if (sexoRepository.existsByNombre(dto.getNombre())) {
            throw new DuplicateResourceException("Sexo", "nombre", dto.getNombre());
        }
        
        Sexo entity = sexoMapper.toEntity(dto);
        Sexo saved = sexoRepository.save(entity);
        return sexoMapper.toDto(saved);
    }

    @Transactional
    public SexoResponseDTO update(Long id, SexoRequestDTO dto) {
        Sexo existing = sexoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Sexo", "ID", id));

        sexoMapper.updateEntityFromDto(dto, existing);
        Sexo updated = sexoRepository.save(existing);
        return sexoMapper.toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!sexoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sexo", "ID", id);
        }
        sexoRepository.deleteById(id);
    }
}

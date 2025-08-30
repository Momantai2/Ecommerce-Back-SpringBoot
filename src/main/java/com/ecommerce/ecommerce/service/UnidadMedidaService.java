package com.ecommerce.ecommerce.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.ecommerce.dto.UnidadMedidaRequestDTO;
import com.ecommerce.ecommerce.dto.UnidadMedidaResponseDTO;
import com.ecommerce.ecommerce.exception.DuplicateResourceException;
import com.ecommerce.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.ecommerce.mapper.UnidadMedidaMapper;
import com.ecommerce.ecommerce.models.UnidadMedida;
import com.ecommerce.ecommerce.repository.UnidadMedidaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnidadMedidaService {

      private final UnidadMedidaRepository unidadMedidaRepository;
    private final UnidadMedidaMapper unidadMedidaMapper;

    //listartodo
    @Transactional(readOnly = true)
    public List<UnidadMedidaResponseDTO> getAll() {
        List<UnidadMedida> list = unidadMedidaRepository.findAll();
        return unidadMedidaMapper.toDtoList(list);
    }

    //listatodo coon paginacion
    public Page<UnidadMedidaResponseDTO> findAll(Pageable pageable) {
        return unidadMedidaRepository.findAll(pageable)
                .map(unidadMedidaMapper::toDto);
    }

//BUSCAR POR ID
    @Transactional(readOnly = true)
    public UnidadMedidaResponseDTO getById(Long id) {
        UnidadMedida entity = unidadMedidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida", "ID", id));
        return unidadMedidaMapper.toDto(entity);
    }
// CREAR REGISTRO

    @Transactional
    public UnidadMedidaResponseDTO create(UnidadMedidaRequestDTO dto) {
        if (unidadMedidaRepository.existsByNombre(dto.getNombre())) {
            throw new DuplicateResourceException("UnidadMedida", "nombre", dto.getNombre());
        }

        UnidadMedida entity = unidadMedidaMapper.toEntity(dto);
        UnidadMedida saved = unidadMedidaRepository.save(entity);
        return unidadMedidaMapper.toDto(saved);
    }

    //ACTUALIZAR REGISTRO
    @Transactional
    public UnidadMedidaResponseDTO update(Long id, UnidadMedidaRequestDTO dto) {
        UnidadMedida existing = unidadMedidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida", "ID", id));

        if (!existing.getNombre().equalsIgnoreCase(dto.getNombre())
                && unidadMedidaRepository.existsByNombre(dto.getNombre())) {
            throw new DuplicateResourceException("UnidadMedida", "nombre", dto.getNombre());
        }

        unidadMedidaMapper.updateEntityFromDto(dto, existing);
        UnidadMedida updated = unidadMedidaRepository.save(existing);
        return unidadMedidaMapper.toDto(updated);
    }

    //ELIMINAR REGISTRO
    @Transactional
    public void delete(Long id) {
        if (!unidadMedidaRepository.existsById(id)) {
            throw new ResourceNotFoundException("UnidadMedida", "ID", id);
        }
        unidadMedidaRepository.deleteById(id);
    }

    //Filtro de busqueda
    @Transactional(readOnly = true)
    public Page<UnidadMedidaResponseDTO> search(String query, Pageable pageable) {
        return unidadMedidaRepository.findByNombreContainingIgnoreCase(
                query, pageable)
                .map(unidadMedidaMapper::toDto);
    }

   public Page<UnidadMedidaResponseDTO> buscarPorNombreYDescripcion(String nombre, Boolean estado, Pageable pageable) {
    return unidadMedidaRepository.buscarPorNombreYEstado(nombre, estado, pageable)
            .map(unidadMedidaMapper::toDto);
}
}

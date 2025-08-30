package com.ecommerce.ecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.ecommerce.dto.EstadoRequestDTO;
import com.ecommerce.ecommerce.dto.EstadoResponseDTO;
import com.ecommerce.ecommerce.exception.DuplicateResourceException;
import com.ecommerce.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.ecommerce.mapper.EstadoMapper;
import com.ecommerce.ecommerce.models.Estado;
import com.ecommerce.ecommerce.repository.EstadoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstadoService {

    private final EstadoRepository estadoRepository;
    private final EstadoMapper estadoMapper;

    //Listatodoconpaginacion
    @Transactional(readOnly = true)
    public Page<EstadoResponseDTO> findAll(Pageable pageable) {
        return estadoRepository.findAll(pageable)
                .map(estadoMapper::toDto);
    }

    //buscapornombre
    @Transactional(readOnly = true)
    public Page<EstadoResponseDTO> search(String search, Pageable pageable) {
        return estadoRepository.findByNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase(
                search, search, pageable)
                .map(estadoMapper::toDto);
    }

    //buscaporid
    @Transactional(readOnly = true)
    public EstadoResponseDTO findById(Long id) {
        Estado estado = estadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado", "ID", id));
        return estadoMapper.toDto(estado);
    }

    //crea un registro
    @Transactional
    public EstadoResponseDTO save(EstadoRequestDTO estadoDTO) {
        if (estadoRepository.existsByNombre(estadoDTO.getNombre())) {
            throw new DuplicateResourceException("Estado", "nombre", estadoDTO.getNombre());
        }
        
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Estado savedEstado = estadoRepository.save(estado);
        return estadoMapper.toDto(savedEstado);
    }

    //actualiza un registro
    @Transactional
    public EstadoResponseDTO update(Long id, EstadoRequestDTO estadoDTO) {
        Estado existingEstado = estadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado", "ID", id));
                
        if (!existingEstado.getNombre().equalsIgnoreCase(estadoDTO.getNombre()) && 
            estadoRepository.existsByNombre(estadoDTO.getNombre())) {
            throw new DuplicateResourceException("Estado", "nombre", estadoDTO.getNombre());
        }
        
        estadoMapper.updateEntityFromDto(estadoDTO, existingEstado);
        Estado updatedEstado = estadoRepository.save(existingEstado);
        return estadoMapper.toDto(updatedEstado);
    }

    //elimina un registro
    @Transactional
    public void delete(Long id) {
        if (!estadoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Estado", "ID", id);
        }
        estadoRepository.deleteById(id);
    }

    //filtrouncampo
@Transactional(readOnly = true)
public Page<EstadoResponseDTO> buscarPorNombreODescripcion(String query, Pageable pageable) {
    return estadoRepository.findByNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase(
            query, query, pageable)
            .map(estadoMapper::toDto);
}

//filtro para dos campos

public Page<EstadoResponseDTO> buscarPorNombreYDescripcion(String nombre, String descripcion, Pageable pageable) {
    return estadoRepository.buscarPorNombreYDescripcion(nombre, descripcion, pageable)
            .map(estadoMapper::toDto);
}


}
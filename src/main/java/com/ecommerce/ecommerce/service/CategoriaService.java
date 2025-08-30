package com.ecommerce.ecommerce.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.ecommerce.dto.CategoriaRequestDTO;
import com.ecommerce.ecommerce.dto.CategoriaResponseDTO;
import com.ecommerce.ecommerce.exception.DuplicateResourceException;
import com.ecommerce.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.ecommerce.mapper.CategoriaMapper;
import com.ecommerce.ecommerce.models.Categoria;
import com.ecommerce.ecommerce.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    //listartodo
    @Transactional(readOnly = true)
    public List<CategoriaResponseDTO> getAll() {
        List<Categoria> list = categoriaRepository.findAll();
        return categoriaMapper.toDtoList(list);
    }

    //listatodo coon paginacion
    public Page<CategoriaResponseDTO> findAll(Pageable pageable) {
        return categoriaRepository.findAll(pageable)
                .map(categoriaMapper::toDto);
    }

//BUSCAR POR ID
    @Transactional(readOnly = true)
    public CategoriaResponseDTO getById(Long id) {
        Categoria entity = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", "ID", id));
        return categoriaMapper.toDto(entity);
    }
// CREAR REGISTRO

    @Transactional
    public CategoriaResponseDTO create(CategoriaRequestDTO dto) {
        if (categoriaRepository.existsByNombre(dto.getNombre())) {
            throw new DuplicateResourceException("Categoria", "nombre", dto.getNombre());
        }

        Categoria entity = categoriaMapper.toEntity(dto);
        Categoria saved = categoriaRepository.save(entity);
        return categoriaMapper.toDto(saved);
    }

    //ACTUALIZAR REGISTRO
    @Transactional
    public CategoriaResponseDTO update(Long id, CategoriaRequestDTO dto) {
        Categoria existing = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", "ID", id));

        if (!existing.getNombre().equalsIgnoreCase(dto.getNombre())
                && categoriaRepository.existsByNombre(dto.getNombre())) {
            throw new DuplicateResourceException("Categoria", "nombre", dto.getNombre());
        }

        categoriaMapper.updateEntityFromDto(dto, existing);
        Categoria updated = categoriaRepository.save(existing);
        return categoriaMapper.toDto(updated);
    }

    //ELIMINAR REGISTRO
    @Transactional
    public void delete(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria", "ID", id);
        }
        categoriaRepository.deleteById(id);
    }

    //Filtro de busqueda
    @Transactional(readOnly = true)
    public Page<CategoriaResponseDTO> search(String query, Pageable pageable) {
        return categoriaRepository.findByNombreContainingIgnoreCase(
                query, pageable)
                .map(categoriaMapper::toDto);
    }

   public Page<CategoriaResponseDTO> buscarPorNombreYDescripcion(String nombre, Boolean estado, Pageable pageable) {
    return categoriaRepository.buscarPorNombreYEstado(nombre, estado, pageable)
            .map(categoriaMapper::toDto);
}
}

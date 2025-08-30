package com.ecommerce.ecommerce.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.ecommerce.dto.ProductoRequestDTO;
import com.ecommerce.ecommerce.dto.ProductoResponseDTO;
import com.ecommerce.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.ecommerce.mapper.ProductoMapper;
import com.ecommerce.ecommerce.models.Categoria;
import com.ecommerce.ecommerce.models.Producto;
import com.ecommerce.ecommerce.models.UnidadMedida;
import com.ecommerce.ecommerce.repository.CategoriaRepository;
import com.ecommerce.ecommerce.repository.ProductoRepository;
import com.ecommerce.ecommerce.repository.UnidadMedidaRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final CategoriaRepository categoriaRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;

    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> getAll() {
        List<Producto> list = productoRepository.findAll();
        return productoMapper.toDtoList(list);
    }

    @Transactional(readOnly = true)
    public Page<ProductoResponseDTO> findAll(Pageable pageable) {
        return productoRepository.findAll(pageable)
                .map(productoMapper::toDto);
    }

    @Transactional(readOnly = true)
    public ProductoResponseDTO getById(Long id) {
        Producto entity = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "ID", id));
        return productoMapper.toDto(entity);
    }

    @Transactional
    public ProductoResponseDTO create(ProductoRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", "ID", dto.getIdCategoria()));
        UnidadMedida unidadMedida = unidadMedidaRepository.findById(dto.getIdUnidadMedida())
                .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida", "ID", dto.getIdUnidadMedida()));

        Producto entity = productoMapper.toEntity(dto);
        entity.setCategoria(categoria);
        entity.setUnidadMedida(unidadMedida);

        Producto saved = productoRepository.save(entity);
        return productoMapper.toDto(saved);
    }

    @Transactional
    public ProductoResponseDTO update(Long id, ProductoRequestDTO dto) {
        Producto existing = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "ID", id));

        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", "ID", dto.getIdCategoria()));
        UnidadMedida unidadMedida = unidadMedidaRepository.findById(dto.getIdUnidadMedida())
                .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida", "ID", dto.getIdUnidadMedida()));

        productoMapper.updateEntityFromDto(dto, existing);
        existing.setCategoria(categoria);
        existing.setUnidadMedida(unidadMedida);

        Producto updated = productoRepository.save(existing);
        return productoMapper.toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto", "ID", id);
        }
        productoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<ProductoResponseDTO> search(String query, Pageable pageable) {
        return productoRepository.findByNombreContainingIgnoreCase(query, pageable)
                .map(productoMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<ProductoResponseDTO> buscarPorNombreYEstado(String nombre, Boolean estado, Pageable pageable) {
        return productoRepository.buscarPorNombreYEstado(nombre, estado, pageable)
                .map(productoMapper::toDto);
    }
}

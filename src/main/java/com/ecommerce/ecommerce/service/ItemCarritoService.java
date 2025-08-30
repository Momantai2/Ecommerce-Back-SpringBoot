package com.ecommerce.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.ecommerce.dto.ItemCarritoRequestDTO;
import com.ecommerce.ecommerce.dto.ItemCarritoResponseDTO;
import com.ecommerce.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.ecommerce.mapper.ItemCarritoMapper;
import com.ecommerce.ecommerce.models.Carrito;
import com.ecommerce.ecommerce.models.ItemCarrito;
import com.ecommerce.ecommerce.models.Producto;
import com.ecommerce.ecommerce.repository.CarritoRepository;
import com.ecommerce.ecommerce.repository.ItemCarritoRepository;
import com.ecommerce.ecommerce.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemCarritoService {

    private final ItemCarritoRepository itemCarritoRepository;
    private final ItemCarritoMapper itemCarritoMapper;
    
    private final ProductoRepository productoRepository;
    private final CarritoRepository carritoRepository;

    @Transactional(readOnly = true)
    public List<ItemCarritoResponseDTO> getAll() {
        List<ItemCarrito> list = itemCarritoRepository.findAll();
        return itemCarritoMapper.toDtoList(list);
    }

    @Transactional(readOnly = true)
    public ItemCarritoResponseDTO getById(Long id) {
        ItemCarrito entity = itemCarritoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("ItemCarrito", "ID", id));
        return itemCarritoMapper.toDto(entity);
    }

   @Transactional
public ItemCarritoResponseDTO create(ItemCarritoRequestDTO dto) {
    Producto producto = productoRepository.findById(dto.getIdProducto())
        .orElseThrow(() -> new ResourceNotFoundException("Producto", "ID", dto.getIdProducto()));

    Carrito carrito = carritoRepository.findById(dto.getIdCarrito())
        .orElseThrow(() -> new ResourceNotFoundException("Carrito", "ID", dto.getIdCarrito()));

    Optional<ItemCarrito> existente = itemCarritoRepository.findByCarritoIdCarritoAndProductoIdProducto(carrito.getIdCarrito(), producto.getIdProducto());

    if (existente.isPresent()) {
        ItemCarrito itemExistente = existente.get();
        itemExistente.setCantidad(itemExistente.getCantidad() + dto.getCantidad());
        ItemCarrito actualizado = itemCarritoRepository.save(itemExistente);
        return itemCarritoMapper.toDto(actualizado);
    }

    ItemCarrito nuevo = itemCarritoMapper.toEntity(dto);
    nuevo.setProducto(producto);
    nuevo.setCarrito(carrito);
    return itemCarritoMapper.toDto(itemCarritoRepository.save(nuevo));
}


    @Transactional
    public ItemCarritoResponseDTO update(Long id, ItemCarritoRequestDTO dto) {
         ItemCarrito existing = itemCarritoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("ItemCarrito", "ID", id));

        Producto producto = productoRepository.findById(dto.getIdProducto())
            .orElseThrow(() -> new ResourceNotFoundException("Producto", "ID", dto.getIdProducto()));

        Carrito carrito = carritoRepository.findById(dto.getIdCarrito())
            .orElseThrow(() -> new ResourceNotFoundException("Carrito", "ID", dto.getIdCarrito()));

        itemCarritoMapper.updateEntityFromDto(dto, existing);
        existing.setProducto(producto);
        existing.setCarrito(carrito);
        ItemCarrito updated = itemCarritoRepository.save(existing);
        return itemCarritoMapper.toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!itemCarritoRepository.existsById(id)) {
            throw new ResourceNotFoundException("ItemCarrito", "ID", id);
        }
        itemCarritoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
public ItemCarrito getByEntity(Long id) {
    return itemCarritoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("ItemCarrito", "ID", id));
}

@Transactional
public ItemCarritoResponseDTO save(ItemCarrito item) {
    return itemCarritoMapper.toDto(itemCarritoRepository.save(item));
}


}

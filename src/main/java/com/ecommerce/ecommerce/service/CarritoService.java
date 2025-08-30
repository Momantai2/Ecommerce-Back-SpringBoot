package com.ecommerce.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dto.CarritoRequestDTO;
import com.ecommerce.ecommerce.dto.CarritoResponseDTO;
import com.ecommerce.ecommerce.dto.ItemCarritoRequestDTO;
import com.ecommerce.ecommerce.dto.ItemCarritoResponseDTO;
import com.ecommerce.ecommerce.models.Carrito;
import com.ecommerce.ecommerce.models.ItemCarrito;
import com.ecommerce.ecommerce.models.Producto;
import com.ecommerce.ecommerce.repository.CarritoRepository;
import com.ecommerce.ecommerce.repository.ItemCarritoRepository;
import com.ecommerce.ecommerce.repository.ProductoRepository;
import com.ecommerce.ecommerce.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final ItemCarritoRepository itemCarritoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    // Obtener todos los carritos
    public List<CarritoResponseDTO> getAll() {
        return carritoRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Obtener carrito por ID
    public CarritoResponseDTO getById(Long id) {
        Carrito carrito = carritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        return mapToDTO(carrito);
    }

    // Crear nuevo carrito
    public CarritoResponseDTO create(CarritoRequestDTO dto) {
        Carrito carrito = new Carrito();
        carrito.setUsuario(usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
carrito.setEstado(true); // ✅ Correcto
        carrito = carritoRepository.save(carrito);
        return mapToDTO(carrito);
    }

    // Actualizar carrito
    public CarritoResponseDTO update(Long id, CarritoRequestDTO dto) {
        Carrito carrito = carritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        carrito.setUsuario(usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
        return mapToDTO(carritoRepository.save(carrito));
    }

    // Eliminar carrito
    public void delete(Long id) {
        carritoRepository.deleteById(id);
    }

    // Obtener el carrito activo del usuario (o crearlo si no existe)
    public CarritoResponseDTO getCarritoByUsuario(Long idUsuario) {
        Carrito carrito = carritoRepository.findByUsuarioIdUsuarioAndEstado(idUsuario,true)
                .orElseGet(() -> {
                    Carrito nuevo = new Carrito();
                    nuevo.setUsuario(usuarioRepository.findById(idUsuario).orElseThrow());
                    nuevo.setEstado(true);
                    return carritoRepository.save(nuevo);
                });

        return mapToDTO(carrito);
    }

    // Agregar producto al carrito
    public void agregarItemACarrito(Long idUsuario, ItemCarritoRequestDTO itemDto) {
        Carrito carrito = carritoRepository.findByUsuarioIdUsuarioAndEstado(idUsuario, true)
                .orElseGet(() -> {
                    Carrito nuevo = new Carrito();
                    nuevo.setUsuario(usuarioRepository.findById(idUsuario).orElseThrow());
                    nuevo.setEstado(true);
                    return carritoRepository.save(nuevo);
                });

        Producto producto = productoRepository.findById(itemDto.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Optional<ItemCarrito> existente = itemCarritoRepository.findByCarritoIdCarritoAndProductoIdProducto(
                carrito.getIdCarrito(), producto.getIdProducto());

        if (existente.isPresent()) {
            ItemCarrito existenteItem = existente.get();
            existenteItem.setCantidad(existenteItem.getCantidad() + itemDto.getCantidad());
            itemCarritoRepository.save(existenteItem);
        } else {
            ItemCarrito item = new ItemCarrito();
            item.setCarrito(carrito);
            item.setProducto(producto);
            item.setCantidad(itemDto.getCantidad());
            itemCarritoRepository.save(item);
        }
    }

    // Convertir entidad a DTO
    private CarritoResponseDTO mapToDTO(Carrito carrito) {
        CarritoResponseDTO dto = new CarritoResponseDTO();
        dto.setIdCarrito(carrito.getIdCarrito());
        dto.setIdUsuario(carrito.getUsuario().getIdUsuario());
        dto.setEstado(carrito.getEstado());

     List<ItemCarritoResponseDTO> items = itemCarritoRepository.findByCarritoIdCarrito(carrito.getIdCarrito())
    .stream()
    .map(item -> {
        ItemCarritoResponseDTO itemDto = new ItemCarritoResponseDTO();
        itemDto.setIdItemCarrito(item.getIdItemCarrito()); // ← FALTABA ESTO
        itemDto.setIdProducto(item.getProducto().getIdProducto());
        itemDto.setNombre(item.getProducto().getNombre());
        itemDto.setPrecio(item.getProducto().getPrecio().doubleValue());
        itemDto.setDescripcion(item.getProducto().getDescripcion());
itemDto.setImagenUrl(item.getProducto().getImagenUrl());
        itemDto.setCantidad(item.getCantidad());
        return itemDto;
    })
    .collect(Collectors.toList());


        dto.setItems(items);
        return dto;
    }
}

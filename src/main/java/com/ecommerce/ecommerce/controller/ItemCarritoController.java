package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.dto.ItemCarritoRequestDTO;
import com.ecommerce.ecommerce.dto.ItemCarritoResponseDTO;
import com.ecommerce.ecommerce.models.ItemCarrito;
import com.ecommerce.ecommerce.service.ItemCarritoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/itemcarrito")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ItemCarritoController {

    private final ItemCarritoService itemCarritoService;

    /**
     * Obtiene todos los ítems de carrito.
     * URL: GET /api/itemcarrito
     */
    @GetMapping
    public ResponseEntity<List<ItemCarritoResponseDTO>> getAll() {
        List<ItemCarritoResponseDTO> items = itemCarritoService.getAll();
        return ResponseEntity.ok(items);
    }

    /**
     * Obtiene un ítem de carrito por su ID.
     * URL: GET /api/itemcarrito/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ItemCarritoResponseDTO> getById(@PathVariable Long id) {
        ItemCarritoResponseDTO item = itemCarritoService.getById(id);
        return ResponseEntity.ok(item);
    }

    /**
     * Crea un nuevo ítem en el carrito.
     * URL: POST /api/itemcarrito
     */
    @PostMapping
    public ResponseEntity<ItemCarritoResponseDTO> create(@Valid @RequestBody ItemCarritoRequestDTO itemDto) {
        ItemCarritoResponseDTO createdItem = itemCarritoService.create(itemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    /**
     * Actualiza un ítem de carrito existente.
     * URL: PUT /api/itemcarrito/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ItemCarritoResponseDTO> update(@PathVariable Long id,
                                                             @Valid @RequestBody ItemCarritoRequestDTO itemDto) {
        ItemCarritoResponseDTO updatedItem = itemCarritoService.update(id, itemDto);
        return ResponseEntity.ok(updatedItem);
    }

    /**
     * Elimina un ítem del carrito por su ID.
     * URL: DELETE /api/itemcarrito/{id}
     */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemCarritoService.delete(id);
        return ResponseEntity.noContent().build();
    }
@PostMapping("/aumentar/{id}")
public ResponseEntity<?> aumentarCantidad(@PathVariable Long id) {
    ItemCarrito item = itemCarritoService.getByEntity(id);
    int stockDisponible = item.getProducto().getStock();

    if (item.getCantidad() >= stockDisponible) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("No hay más stock disponible para este producto.");
    }

    item.setCantidad(item.getCantidad() + 1);
    return ResponseEntity.ok(itemCarritoService.save(item));
}



@PostMapping("/disminuir/{id}")
public ResponseEntity<?> disminuirCantidad(@PathVariable Long id) {
    ItemCarrito item = itemCarritoService.getByEntity(id);
    if (item.getCantidad() > 1) {
        item.setCantidad(item.getCantidad() - 1);
        return ResponseEntity.ok(itemCarritoService.save(item));
    } else {
        itemCarritoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
}

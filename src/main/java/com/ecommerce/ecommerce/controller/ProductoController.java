package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.dto.ProductoRequestDTO;
import com.ecommerce.ecommerce.dto.ProductoResponseDTO;
import com.ecommerce.ecommerce.service.ProductoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {

  private final ProductoService productoService;

      

    @GetMapping
    public ResponseEntity<Page<ProductoResponseDTO>> getAllCategorias(
            Pageable pageable,
            @RequestParam(required = false) String search) {

        if (search != null && !search.trim().isEmpty()) {
            return ResponseEntity.ok(productoService.search(search, pageable));
        }
        return ResponseEntity.ok(productoService.findAll(pageable));
    }

 
  
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> getCategoriaById(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.getById(id));
    }

 
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> createCategoria(@Valid @RequestBody ProductoRequestDTO categoriaDto) {
        ProductoResponseDTO createdCategoria = productoService.create(categoriaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoria);
    }

 
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> updateCategoria(@PathVariable Long id,
                                                                 @Valid @RequestBody ProductoRequestDTO categoriaDto) {
        ProductoResponseDTO updatedCategoria = productoService.update(id, categoriaDto);
        return ResponseEntity.ok(updatedCategoria);
    }

  
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }


@GetMapping("/search")
public ResponseEntity<Page<ProductoResponseDTO>> searchcategoria(
        @RequestParam(value = "nombre", required = false) String nombre,
        @RequestParam(value = "estado", required = false) Boolean estado,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
) {
    Pageable pageable = PageRequest.of(page, size);
    Page<ProductoResponseDTO> producto;

    if ((nombre != null && !nombre.trim().isEmpty()) || estado != null) {
        producto = productoService.buscarPorNombreYEstado(nombre, estado, pageable);
    } else {
        producto = productoService.findAll(pageable);
    }

    return ResponseEntity.ok(producto);
}

 @GetMapping("/todo")
    public ResponseEntity<List<ProductoResponseDTO>> getAll() {
        List<ProductoResponseDTO> producto = productoService.getAll();
        return ResponseEntity.ok(producto);
    }

} 


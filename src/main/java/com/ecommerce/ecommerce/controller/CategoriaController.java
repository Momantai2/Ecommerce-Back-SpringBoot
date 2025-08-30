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

import com.ecommerce.ecommerce.dto.CategoriaRequestDTO;
import com.ecommerce.ecommerce.dto.CategoriaResponseDTO;
import com.ecommerce.ecommerce.service.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {

    private final CategoriaService categoriaService;

        public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<Page<CategoriaResponseDTO>> getAllCategorias(
            Pageable pageable,
            @RequestParam(required = false) String search) {

        if (search != null && !search.trim().isEmpty()) {
            return ResponseEntity.ok(categoriaService.search(search, pageable));
        }
        return ResponseEntity.ok(categoriaService.findAll(pageable));
    }

 
  
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> getCategoriaById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.getById(id));
    }

 
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> createCategoria(@Valid @RequestBody CategoriaRequestDTO categoriaDto) {
        CategoriaResponseDTO createdCategoria = categoriaService.create(categoriaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoria);
    }

 
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> updateCategoria(@PathVariable Long id,
                                                                 @Valid @RequestBody CategoriaRequestDTO categoriaDto) {
        CategoriaResponseDTO updatedCategoria = categoriaService.update(id, categoriaDto);
        return ResponseEntity.ok(updatedCategoria);
    }

  
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }


@GetMapping("/search")
public ResponseEntity<Page<CategoriaResponseDTO>> searchcategoria(
        @RequestParam(value = "nombre", required = false) String nombre,
        @RequestParam(value = "estado", required = false) Boolean estado,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
) {
    Pageable pageable = PageRequest.of(page, size);
    Page<CategoriaResponseDTO> categorias;

    if ((nombre != null && !nombre.trim().isEmpty()) || estado != null) {
        categorias = categoriaService.buscarPorNombreYDescripcion(nombre, estado, pageable);
    } else {
        categorias = categoriaService.findAll(pageable);
    }

    return ResponseEntity.ok(categorias);
}

 @GetMapping("/todo")
    public ResponseEntity<List<CategoriaResponseDTO>> getAll() {
        List<CategoriaResponseDTO> categorias = categoriaService.getAll();
        return ResponseEntity.ok(categorias);
    }

} 


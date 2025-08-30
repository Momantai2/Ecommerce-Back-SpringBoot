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

import com.ecommerce.ecommerce.dto.UnidadMedidaRequestDTO;
import com.ecommerce.ecommerce.dto.UnidadMedidaResponseDTO;
import com.ecommerce.ecommerce.service.UnidadMedidaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/unidades-medida")
@CrossOrigin(origins = "http://localhost:4200")
public class UnidadMedidaController {

    private final UnidadMedidaService unidadMedidaService;

        public UnidadMedidaController(UnidadMedidaService unidadMedidaService) {
        this.unidadMedidaService = unidadMedidaService;
    }

    @GetMapping
    public ResponseEntity<Page<UnidadMedidaResponseDTO>> getAllCategorias(
            Pageable pageable,
            @RequestParam(required = false) String search) {

        if (search != null && !search.trim().isEmpty()) {
            return ResponseEntity.ok(unidadMedidaService.search(search, pageable));
        }
        return ResponseEntity.ok(unidadMedidaService.findAll(pageable));
    }

 
  
    @GetMapping("/{id}")
    public ResponseEntity<UnidadMedidaResponseDTO> getCategoriaById(@PathVariable Long id) {
        return ResponseEntity.ok(unidadMedidaService.getById(id));
    }

 
    @PostMapping
    public ResponseEntity<UnidadMedidaResponseDTO> createCategoria(@Valid @RequestBody UnidadMedidaRequestDTO categoriaDto) {
        UnidadMedidaResponseDTO createdCategoria = unidadMedidaService.create(categoriaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoria);
    }

 
    @PutMapping("/{id}")
    public ResponseEntity<UnidadMedidaResponseDTO> updateCategoria(@PathVariable Long id,
                                                                 @Valid @RequestBody UnidadMedidaRequestDTO categoriaDto) {
        UnidadMedidaResponseDTO updatedCategoria = unidadMedidaService.update(id, categoriaDto);
        return ResponseEntity.ok(updatedCategoria);
    }

  
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        unidadMedidaService.delete(id);
        return ResponseEntity.noContent().build();
    }


@GetMapping("/search")
public ResponseEntity<Page<UnidadMedidaResponseDTO>> searchcategoria(
        @RequestParam(value = "nombre", required = false) String nombre,
        @RequestParam(value = "estado", required = false) Boolean estado,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
) {
    Pageable pageable = PageRequest.of(page, size);
    Page<UnidadMedidaResponseDTO> categorias;

    if ((nombre != null && !nombre.trim().isEmpty()) || estado != null) {
        categorias = unidadMedidaService.buscarPorNombreYDescripcion(nombre, estado, pageable);
    } else {
        categorias = unidadMedidaService.findAll(pageable);
    }

    return ResponseEntity.ok(categorias);
}

 @GetMapping("/todo")
    public ResponseEntity<List<UnidadMedidaResponseDTO>> getAll() {
        List<UnidadMedidaResponseDTO> categorias = unidadMedidaService.getAll();
        return ResponseEntity.ok(categorias);
    }

} 


package com.ecommerce.ecommerce.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.ecommerce.ecommerce.dto.EstadoRequestDTO;
import com.ecommerce.ecommerce.dto.EstadoResponseDTO;
import com.ecommerce.ecommerce.service.EstadoService;

@RestController
@RequestMapping("/api/estados")
@CrossOrigin(origins = "http://localhost:4200")

public class EstadoController {

    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    public ResponseEntity<Page<EstadoResponseDTO>> getAllEstados(
            Pageable pageable,
            @RequestParam(required = false) String search) {
        
        if (search != null && !search.isEmpty()) {
            return ResponseEntity.ok(estadoService.search(search, pageable));
        }
        return ResponseEntity.ok(estadoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoResponseDTO> getEstadoById(@PathVariable Long id) {
        return ResponseEntity.ok(estadoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EstadoResponseDTO> createEstado(@RequestBody EstadoRequestDTO estadoDTO) {
        return ResponseEntity.ok(estadoService.save(estadoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoResponseDTO> updateEstado(
            @PathVariable Long id, 
            @RequestBody EstadoRequestDTO estadoDTO) {
        return ResponseEntity.ok(estadoService.update(id, estadoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstado(@PathVariable Long id) {
        estadoService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
@GetMapping("/search")
public ResponseEntity<Page<EstadoResponseDTO>> searchEstados(
        @RequestParam(value = "nombre", required = false) String nombre,
        @RequestParam(value = "descripcion", required = false) String descripcion,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
) {
    Pageable pageable = PageRequest.of(page, size);
    Page<EstadoResponseDTO> estados;

    if ((nombre != null && !nombre.trim().isEmpty()) || 
        (descripcion != null && !descripcion.trim().isEmpty())) {
        
        estados = estadoService.buscarPorNombreYDescripcion(nombre, descripcion, pageable);
    } else {
        estados = estadoService.findAll(pageable);
    }

    return ResponseEntity.ok(estados);
}


}

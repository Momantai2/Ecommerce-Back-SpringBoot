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

import com.ecommerce.ecommerce.dto.SexoRequestDTO;
import com.ecommerce.ecommerce.dto.SexoResponseDTO;
import com.ecommerce.ecommerce.service.SexoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sexo")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class SexoController {

    private final SexoService sexoService;

    @GetMapping
    public ResponseEntity<List<SexoResponseDTO>> getAllSexos() {
        List<SexoResponseDTO> sexos = sexoService.getAll();
        return ResponseEntity.ok(sexos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SexoResponseDTO> getSexoById(@PathVariable Long id) {
        SexoResponseDTO sexo = sexoService.getById(id);
        return ResponseEntity.ok(sexo);
    }

    @PostMapping
    public ResponseEntity<SexoResponseDTO> createSexo(@Valid @RequestBody SexoRequestDTO dto) {
        SexoResponseDTO createdSexo = sexoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSexo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SexoResponseDTO> updateSexo(@PathVariable Long id, @Valid @RequestBody SexoRequestDTO dto) {
        SexoResponseDTO updatedSexo = sexoService.update(id, dto);
        return ResponseEntity.ok(updatedSexo);
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSexo(@PathVariable Long id) {
        sexoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

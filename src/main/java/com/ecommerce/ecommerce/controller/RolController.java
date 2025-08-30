package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.dto.RolRequestDTO;
import com.ecommerce.ecommerce.dto.RolResponseDTO;
import com.ecommerce.ecommerce.service.RolService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RolController {

    private final RolService rolService;

    /**
     * Obtiene todos los roles.
     * URL: GET /api/roles
     */
    @GetMapping
    public ResponseEntity<List<RolResponseDTO>> getAllRoles() {
        List<RolResponseDTO> roles = rolService.getAll();
        return ResponseEntity.ok(roles);
    }

    /**
     * Obtiene un rol por su ID.
     * URL: GET /api/roles/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<RolResponseDTO> getRolById(@PathVariable Long id) {
        RolResponseDTO rol = rolService.getById(id);
        return ResponseEntity.ok(rol);
    }

    /**
     * Crea un nuevo rol.
     * URL: POST /api/roles
     */
    @PostMapping
    public ResponseEntity<RolResponseDTO> createRol(@Valid @RequestBody RolRequestDTO rolDto) {
        RolResponseDTO createdRol = rolService.create(rolDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRol);
    }

    /**
     * Actualiza un rol existente.
     * URL: PUT /api/roles/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<RolResponseDTO> updateRol(@PathVariable Long id,
                                                    @Valid @RequestBody RolRequestDTO rolDto) {
        RolResponseDTO updatedRol = rolService.update(id, rolDto);
        return ResponseEntity.ok(updatedRol);
    }

    /**
     * Elimina un rol por su ID.
     * URL: DELETE /api/roles/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRol(@PathVariable Long id) {
        rolService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

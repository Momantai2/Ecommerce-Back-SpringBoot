package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.dto.UsuarioRequestDTO;
import com.ecommerce.ecommerce.dto.UsuarioResponseDTO;
import com.ecommerce.ecommerce.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    private final UsuarioService usuarioService;

    /**
     * Maneja las solicitudes GET para obtener todos los usuarios.
     */
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAllUsuarios() {
        List<UsuarioResponseDTO> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    /**
     * Maneja las solicitudes GET para obtener un usuario por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getUsuarioById(@PathVariable Long id) {
        UsuarioResponseDTO usuario = usuarioService.getUsuarioById(id);
        return ResponseEntity.ok(usuario);
    }

    /**
     * Maneja las solicitudes POST para crear un nuevo usuario.
     */
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> createUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioDto) {
        UsuarioResponseDTO createdUsuario = usuarioService.createUsuario(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUsuario);
    }

    /**
     * Maneja las solicitudes PUT para actualizar un usuario existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> updateUsuario(@PathVariable Long id,
                                                             @Valid @RequestBody UsuarioRequestDTO usuarioDto) {
        UsuarioResponseDTO updatedUsuario = usuarioService.updateUsuario(id, usuarioDto);
        return ResponseEntity.ok(updatedUsuario);
    }

    /**
     * Maneja las solicitudes DELETE para eliminar un usuario por su ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}

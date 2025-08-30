package com.ecommerce.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.config.JwtUtil;
import com.ecommerce.ecommerce.dto.JwtResponse;
import com.ecommerce.ecommerce.dto.LoginRequest;
import com.ecommerce.ecommerce.models.Usuario;
import com.ecommerce.ecommerce.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private JwtUtil jwtUtil;

   @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    String username = request.getUsername().trim();
    String password = request.getPassword().trim();

    Usuario usuario = usuarioRepo.findByNombreUsuario(username);

    if (usuario == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
    }

    if (!usuario.getPassword().equals(password)) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
    }

    Long idRol = null;
    String rolNombre = "ROLE_DEFAULT";

    if (usuario.getRol() != null) {
        idRol = usuario.getRol().getIdRol();
    }

    String token = jwtUtil.generateToken(username, rolNombre);

    JwtResponse response = new JwtResponse(token, idRol);

    return ResponseEntity.ok(response);
}

}

package com.ecommerce.ecommerce.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.config.JwtUtil;
import com.ecommerce.ecommerce.dto.CarritoRequestDTO;
import com.ecommerce.ecommerce.dto.CarritoResponseDTO;
import com.ecommerce.ecommerce.dto.ItemCarritoRequestDTO;
import com.ecommerce.ecommerce.models.Usuario;
import com.ecommerce.ecommerce.repository.UsuarioRepository;
import com.ecommerce.ecommerce.service.CarritoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/carritos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CarritoController {

    private final CarritoService carritoService;
    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;

    /**
     * Obtiene todos los carritos.
     */
    @GetMapping
    public ResponseEntity<List<CarritoResponseDTO>> getAllCarritos() {
        List<CarritoResponseDTO> carritos = carritoService.getAll();
        return ResponseEntity.ok(carritos);
    }

    /**
     * Obtiene un carrito por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CarritoResponseDTO> getCarritoById(@PathVariable Long id) {
        CarritoResponseDTO carrito = carritoService.getById(id);
        return ResponseEntity.ok(carrito);
    }

    /**
     * Crea un nuevo carrito.
     */
    @PostMapping
    public ResponseEntity<CarritoResponseDTO> createCarrito(@Valid @RequestBody CarritoRequestDTO carritoDto) {
        CarritoResponseDTO createdCarrito = carritoService.create(carritoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCarrito);
    }

    /**
     * Actualiza un carrito existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CarritoResponseDTO> updateCarrito(@PathVariable Long id,
                                                             @Valid @RequestBody CarritoRequestDTO carritoDto) {
        CarritoResponseDTO updatedCarrito = carritoService.update(id, carritoDto);
        return ResponseEntity.ok(updatedCarrito);
    }

    /**
     * Elimina un carrito por su ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrito(@PathVariable Long id) {
        carritoService.delete(id);
        return ResponseEntity.noContent().build();
    }

     @GetMapping("/usuario")
    public ResponseEntity<CarritoResponseDTO> getCarritoByToken(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);
        Usuario usuario = usuarioRepository.findByNombreUsuario(username);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        CarritoResponseDTO carrito = carritoService.getCarritoByUsuario(usuario.getIdUsuario());
        return ResponseEntity.ok(carrito);
    }

    /**
     * ➕ Agrega un producto al carrito del usuario autenticado.
     */
    @PostMapping("/usuario/agregar")
    public ResponseEntity<?> agregarProductoAlCarrito(@RequestHeader("Authorization") String authHeader,
                                                      @RequestBody ItemCarritoRequestDTO item) {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);
        Usuario usuario = usuarioRepository.findByNombreUsuario(username);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        carritoService.agregarItemACarrito(usuario.getIdUsuario(), item);
        return ResponseEntity.ok().build();
    }

@PostMapping("/usuario/transferir")
public ResponseEntity<?> transferirCarritoLocalAlUsuario(
        @RequestHeader("Authorization") String authHeader,
        @RequestBody List<ItemCarritoRequestDTO> items) {

    String token = authHeader.replace("Bearer ", "");
    String username = jwtUtil.extractUsername(token);
    Usuario usuario = usuarioRepository.findByNombreUsuario(username);

    if (usuario == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    for (ItemCarritoRequestDTO item : items) {
        carritoService.agregarItemACarrito(usuario.getIdUsuario(), item);
    }

return ResponseEntity.ok(Map.of("mensaje", "Carrito local transferido correctamente al usuario"));
}


}

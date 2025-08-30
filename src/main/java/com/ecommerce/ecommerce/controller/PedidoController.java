package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.config.JwtUtil;
import com.ecommerce.ecommerce.dto.PedidoRequestDTO;
import com.ecommerce.ecommerce.dto.PedidoResponseDTO;
import com.ecommerce.ecommerce.models.Usuario;
import com.ecommerce.ecommerce.repository.UsuarioRepository;
import com.ecommerce.ecommerce.service.PedidoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PedidoController {

    private final PedidoService pedidoService;
    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;

    @PostMapping("/procesar")
    public ResponseEntity<?> procesarPago(@RequestHeader("Authorization") String tokenHeader) {
        String token = tokenHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);
        Usuario usuario = usuarioRepository.findByNombreUsuario(username);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            pedidoService.procesarPagoExitoso(usuario.getIdUsuario());
return ResponseEntity.ok(java.util.Map.of("mensaje", "Pedido procesado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listarPedidos(@RequestHeader("Authorization") String tokenHeader) {
        String token = tokenHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);
        Usuario usuario = usuarioRepository.findByNombreUsuario(username);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        boolean esAdmin = usuario.getRol().getIdRol() == 1; // Asumiendo que rol 1 es admin

        List<PedidoResponseDTO> pedidos = pedidoService.obtenerPedidosPorUsuario(usuario.getIdUsuario(), esAdmin);
        return ResponseEntity.ok(pedidos);
    }
@PutMapping("/{idPedido}/estado")
public ResponseEntity<?> actualizarEstadoPedido(@PathVariable Long idPedido,
                                                @RequestBody PedidoRequestDTO estadoRequest,
                                                @RequestHeader("Authorization") String tokenHeader) {
    // Extraer token y validar usuario
    String token = tokenHeader.replace("Bearer ", "");
    String username = jwtUtil.extractUsername(token);
    Usuario usuario = usuarioRepository.findByNombreUsuario(username);

    if (usuario == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    try {
        boolean esAdmin = usuario.getRol().getIdRol() == 1;
        pedidoService.actualizarEstadoPedido(idPedido, usuario.getIdUsuario(), esAdmin, estadoRequest.getEstado());

        return ResponseEntity.ok().body(java.util.Map.of("mensaje", "Estado actualizado correctamente"));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(java.util.Map.of("error", e.getMessage()));
    }
}


@GetMapping("/{idPedido}/boleta")
public ResponseEntity<byte[]> generarBoleta(@PathVariable Long idPedido,
                                            @RequestHeader("Authorization") String tokenHeader) {
    try {
        byte[] pdf = pedidoService.generarBoletaPDF(idPedido);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "boleta-pedido-" + idPedido + ".pdf");

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
    
     }
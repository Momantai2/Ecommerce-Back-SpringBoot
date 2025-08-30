package com.ecommerce.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class PedidoResponseDTO {
    private Long idPedido;
    private Long idUsuario;
    private String nombreUsuario;
    private LocalDateTime fecha;
    private Double total;
    private EstadoResponseDTO estado; // objeto con info completa
    private List<ItemPedidoResponseDTO> items;
}



package com.ecommerce.ecommerce.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;


@Data
public class DashboardResumenDTO {
    private long totalUsuarios;
    private long totalProductos;
    private long totalPedidos;
    private Double totalIngresos;
    private Map<String, Long> pedidosPorEstado; // ejemplo: {"pendiente": 5, "entregado": 10}
    private Map<String, Double> ventasMensuales; // ejemplo: {"Enero": 2500.50, "Febrero": 3000.00}
    private List<ProductosmasvendidosDTO> productosMasVendidos;
    private List<StockProductoDTO> lowStockProducts;
private List<CategoriaVendidaDTO> categoriasMasVendidas;

};



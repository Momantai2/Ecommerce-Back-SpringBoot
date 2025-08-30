package com.ecommerce.ecommerce.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dto.CategoriaVendidaDTO;
import com.ecommerce.ecommerce.dto.DashboardResumenDTO;
import com.ecommerce.ecommerce.dto.ProductosmasvendidosDTO;
import com.ecommerce.ecommerce.dto.StockProductoDTO;
import com.ecommerce.ecommerce.repository.PedidoRepository;
import com.ecommerce.ecommerce.repository.ProductoRepository;
import com.ecommerce.ecommerce.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final PedidoRepository pedidoRepository;

    public DashboardResumenDTO obtenerResumen() {
        DashboardResumenDTO dto = new DashboardResumenDTO();

        dto.setTotalUsuarios(usuarioRepository.count());
        dto.setTotalProductos(productoRepository.count());
        dto.setTotalPedidos(pedidoRepository.count());

        Double totalIngresos = pedidoRepository.sumTotalIngresos();
        dto.setTotalIngresos(totalIngresos != null ? totalIngresos : 0.0);

        // Pedidos por estado
        List<Object[]> resultado = pedidoRepository.countPedidosPorEstado();
        Map<String, Long> resumen = new HashMap<>();
        for (Object[] row : resultado) {
            resumen.put((String) row[0], ((Number) row[1]).longValue());
        }
        dto.setPedidosPorEstado(resumen);

        // Ventas por mes
        List<Object[]> resultados = pedidoRepository.ventasPorMes();
        Map<String, Double> ventasMensuales = new LinkedHashMap<>();
        for (Object[] fila : resultados) {
            String mes = (String) fila[0];
            Double total = ((Number) fila[1]).doubleValue();
            ventasMensuales.put(mes, total);
        }
        dto.setVentasMensuales(ventasMensuales);

        // Productos más vendidos
        List<Object[]> topVendidos = pedidoRepository.findProductosMasVendidos();
        List<ProductosmasvendidosDTO> productosMasVendidos = new ArrayList<>();
        for (Object[] fila : topVendidos) {
            String nombre = (String) fila[0];
            Long cantidad = ((Number) fila[1]).longValue();
            productosMasVendidos.add(new ProductosmasvendidosDTO(nombre, cantidad));
        }
        dto.setProductosMasVendidos(productosMasVendidos);

          // Productos con stock bajo (≤5 unidades)
        List<Object[]> lowStock = productoRepository.findLowStockProducts(10);
        dto.setLowStockProducts(
            lowStock.stream()
                    .map(r -> new StockProductoDTO((String)r[0], ((Number)r[1]).intValue()))
                    .toList()
        );

        // Categorías más vendidas
        List<Object[]> catVendidas = productoRepository.findSoldQuantityByCategory();
        dto.setCategoriasMasVendidas(
            catVendidas.stream()
                        .map(r -> new CategoriaVendidaDTO((String)r[0], ((Number)r[1]).longValue()))
                        .toList()
        );

        // Pedidos por estado
        List<Object[]> pedEstado = pedidoRepository.countPedidosPorEstado();
        dto.setPedidosPorEstado(
            pedEstado.stream()
                      .collect(Collectors.toMap(r -> (String)r[0], r -> ((Number)r[1]).longValue()))
        );
        
        return dto;
    }
}

package com.ecommerce.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.ecommerce.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByUsuarioIdUsuario(Long idUsuario);

    // Suma el total solo de pedidos cuyo estado.nombre es 'PAGADO'
    @Query("SELECT SUM(p.total) FROM Pedido p WHERE p.estado.nombre = 'Pagado'")
    Double sumTotalIngresos();

    // Cuenta la cantidad de pedidos agrupados por estado (objeto Estado)
    @Query("SELECT p.estado.nombre, COUNT(p) FROM Pedido p GROUP BY p.estado.nombre")
    List<Object[]> countPedidosPorEstado();

    // Ventas por mes, solo de pedidos 'PAGADO'
    // Ajustamos para que la columna de estado sea 'id_estado' y comparamos con id del estado PAGADO
    // Pero como usás nativeQuery, mejor pasar nombre estado y hacer join
    @Query(value = """
        SELECT DATE_FORMAT(p.fecha, '%M') AS mes, SUM(p.total) AS total
        FROM pedido p
       JOIN estado e ON p.id_estado = e.id_estado
        WHERE e.nombre = 'Pagado'
        GROUP BY DATE_FORMAT(p.fecha, '%M'), MONTH(p.fecha)
        ORDER BY MONTH(p.fecha)
    """, nativeQuery = true)
    List<Object[]> ventasPorMes();

    // Productos más vendidos (sin cambio, ya que usas nativo y tablas/columnas correctas)
    @Query(value = """
        SELECT pr.nombre AS nombre, SUM(ip.cantidad) AS cantidadVendida
        FROM item_pedido ip
        JOIN producto pr ON ip.id_producto = pr.id_producto
        GROUP BY pr.nombre
        ORDER BY cantidadVendida DESC
        LIMIT 5
    """, nativeQuery = true)
    List<Object[]> findProductosMasVendidos();

    @Override
    Optional<Pedido> findById(Long idPedido);
}

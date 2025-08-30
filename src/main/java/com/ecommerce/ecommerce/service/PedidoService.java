package com.ecommerce.ecommerce.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dto.PedidoResponseDTO;
import com.ecommerce.ecommerce.mapper.PedidoMapper;
import com.ecommerce.ecommerce.models.Carrito;
import com.ecommerce.ecommerce.models.Estado;
import com.ecommerce.ecommerce.models.ItemCarrito;
import com.ecommerce.ecommerce.models.ItemPedido;
import com.ecommerce.ecommerce.models.Pedido;
import com.ecommerce.ecommerce.models.Producto;
import com.ecommerce.ecommerce.repository.CarritoRepository;
import com.ecommerce.ecommerce.repository.EstadoRepository;
import com.ecommerce.ecommerce.repository.ItemCarritoRepository;
import com.ecommerce.ecommerce.repository.PedidoRepository;
import com.ecommerce.ecommerce.repository.ProductoRepository;
import com.ecommerce.ecommerce.repository.UsuarioRepository;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final CarritoRepository carritoRepository;
    private final ItemCarritoRepository itemCarritoRepository;
    private final ProductoRepository productoRepository;
    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PedidoMapper pedidoMapper;
    private final EstadoRepository estadoRepository;


    @Transactional
    public void procesarPagoExitoso(Long idUsuario) {
        Carrito carrito = carritoRepository.findByUsuarioIdUsuarioAndEstado(idUsuario, true)
            .orElseThrow(() -> new RuntimeException("Carrito no encontrado para el usuario"));

        List<ItemCarrito> items = itemCarritoRepository.findByCarritoIdCarrito(carrito.getIdCarrito());
        if (items.isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }

        for (ItemCarrito item : items) {
            if (item.getCantidad() > item.getProducto().getStock()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + item.getProducto().getNombre());
            }
        }

        Pedido pedido = new Pedido();
        pedido.setUsuario(carrito.getUsuario());
        pedido.setFecha(LocalDateTime.now());
     Estado estadoPagado = estadoRepository.findById(1L)
        .orElseThrow(() -> new RuntimeException("Estado 'Pagado' no encontrado"));

pedido.setEstado(estadoPagado);



        List<ItemPedido> itemPedidos = new ArrayList<>();
        double total = 0;

        for (ItemCarrito item : items) {
            Producto producto = item.getProducto();
            producto.setStock(producto.getStock() - item.getCantidad());
            productoRepository.save(producto);

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setProducto(producto);
            itemPedido.setCantidad(item.getCantidad());
            itemPedido.setPrecioUnitario(producto.getPrecio().doubleValue());
            itemPedido.setPedido(pedido);

            total += item.getCantidad() * producto.getPrecio().doubleValue();
            itemPedidos.add(itemPedido);
        }

        pedido.setItems(itemPedidos);
        pedido.setTotal(total);
        pedidoRepository.save(pedido);
        itemCarritoRepository.deleteAll(items);
    }

    public List<PedidoResponseDTO> obtenerPedidosPorUsuario(Long idUsuario, boolean esAdmin) {
        List<Pedido> pedidos;

        if (esAdmin) {
            pedidos = pedidoRepository.findAll();
        } else {
            pedidos = pedidoRepository.findByUsuarioIdUsuario(idUsuario);
        }

        return pedidos.stream()
                      .map(pedidoMapper::toDto)
                      .collect(Collectors.toList());
    }
    
@Transactional
public void actualizarEstadoPedido(Long idPedido, Long idUsuario, boolean esAdmin, String nuevoEstado) {
    Pedido pedido = pedidoRepository.findById(idPedido)
        .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

    if (!esAdmin && !pedido.getUsuario().getIdUsuario().equals(idUsuario)) {
        throw new RuntimeException("No autorizado para modificar este pedido");
    }

    List<String> estadosPermitidos = List.of("Cancelado", "Pagado", "Pendiente", "En proceso");
    if (!estadosPermitidos.contains(nuevoEstado)) {
        throw new RuntimeException("Estado no permitido: " + nuevoEstado);
    }

    if (pedido.getEstado().getNombre().equalsIgnoreCase(nuevoEstado)) {
        throw new RuntimeException("El pedido ya se encuentra en el estado: " + nuevoEstado);
    }

    Estado estado = estadoRepository.findByNombreIgnoreCase(nuevoEstado)
        .orElseThrow(() -> new RuntimeException("Estado no encontrado: " + nuevoEstado));
    
    pedido.setEstado(estado);
    pedidoRepository.save(pedido);
}


public byte[] generarBoletaPDF(Long idPedido) {
    Pedido pedido = pedidoRepository.findById(idPedido)
        .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PdfWriter writer = new PdfWriter(baos);
    PdfDocument pdf = new PdfDocument(writer);
    Document document = new Document(pdf);

    // Título
    document.add(new Paragraph("BOLETA DE COMPRA").setBold().setFontSize(18));
    document.add(new Paragraph("Fecha: " + pedido.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
    document.add(new Paragraph("Cliente: " + pedido.getUsuario().getNombreUsuario()));
    document.add(new Paragraph("Pedido N°: " + pedido.getIdPedido()));
    document.add(new Paragraph(" ")); // espacio

    // Tabla con 4 columnas: Producto, Cantidad, Precio Unitario, Subtotal
    Table table = new Table(UnitValue.createPercentArray(new float[]{4, 2, 2, 2}))
                    .useAllAvailableWidth();

    // Encabezados en negrita
    table.addHeaderCell(new Paragraph("Producto").setBold());
    table.addHeaderCell(new Paragraph("Cantidad").setBold());
    table.addHeaderCell(new Paragraph("Precio Unitario").setBold());
    table.addHeaderCell(new Paragraph("Subtotal").setBold());

    // Filas con datos
    for (ItemPedido item : pedido.getItems()) {
        table.addCell(item.getProducto().getNombre());
        table.addCell(String.valueOf(item.getCantidad()));
        table.addCell(String.format("S/ %.2f", item.getPrecioUnitario()));
        double subtotal = item.getCantidad() * item.getPrecioUnitario();
        table.addCell(String.format("S/ %.2f", subtotal));
    }

    document.add(table);

    // Separador
    document.add(new Paragraph(" "));

    // Total destacado
    document.add(new Paragraph("Total: S/ " + String.format("%.2f", pedido.getTotal()))
                 .setBold()
                 .setFontSize(14)
                 .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.RIGHT));

    document.close();
    return baos.toByteArray();
}


}

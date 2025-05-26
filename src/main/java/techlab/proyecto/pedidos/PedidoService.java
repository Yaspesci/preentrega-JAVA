package techlab.proyecto.pedidos;

import techlab.proyecto.excepciones.StockInsuficienteException;
import techlab.proyecto.productos.Producto;
import techlab.proyecto.productos.ProductoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PedidoService {
    private List<Pedido> pedidos;
    private ProductoService productoService;

    public PedidoService(ProductoService productoService) {
        this.productoService = productoService;
        this.pedidos = new ArrayList<>();
    }

    public void crearPedido() {
        Scanner scanner = new Scanner(System.in);
        Pedido nuevoPedido = new Pedido();

        System.out.print("¿Cuántos productos desea agregar al pedido?: ");
        int cantidadProductos;
        try {
            cantidadProductos = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Número inválido.");
            return;
        }

        for (int i = 0; i < cantidadProductos; i++) {
            try {
                System.out.print("Ingrese el ID del producto: ");
                int idProducto = Integer.parseInt(scanner.nextLine());
                Producto p = productoService.buscarPorId(idProducto);
                if (p == null) {
                    System.out.println("Producto no encontrado.");
                    i--; continue;
                }

                System.out.print("Cantidad deseada de " + p.getNombre() + ": ");
                int cantidadDeseada = Integer.parseInt(scanner.nextLine());

                if (cantidadDeseada <= 0) {
                    System.out.println("La cantidad debe ser mayor a 0.");
                    i--; continue;
                }

                if (p.getStock() < cantidadDeseada) {
                    throw new StockInsuficienteException("Stock insuficiente para el producto: " + p.getNombre() + ". Disponible: " + p.getStock());
                }

                p.setStock(p.getStock() - cantidadDeseada);
                LineaPedido linea = new LineaPedido(p, cantidadDeseada);
                nuevoPedido.agregarLinea(linea);

            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Intente de nuevo.");
                i--;
            } catch (StockInsuficienteException e) {
                System.out.println(e.getMessage());
                i--;
            }
        }

        pedidos.add(nuevoPedido);
        System.out.println("Pedido creado exitosamente:");
        System.out.println(nuevoPedido);
    }

    public void listarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
        } else {
            for (Pedido p : pedidos) {
                System.out.println(p);
                System.out.println("--------------------");
            }
        }
    }
}
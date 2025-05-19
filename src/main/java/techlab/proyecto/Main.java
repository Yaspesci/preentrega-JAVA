package techlab.proyecto;


import techlab.proyecto.pedidos.PedidoService;
import techlab.proyecto.productos.ProductoService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductoService productoService = new ProductoService();
        PedidoService pedidoService = new PedidoService(productoService);

        int option = 0;

        do {
            System.out.println("\n==============================");
            System.out.println("  SISTEMA DE GESTIÓN - TECHLAB");
            System.out.println("==============================");
            System.out.println("1) Agregar producto");
            System.out.println("2) Listar productos");
            System.out.println("3) Buscar/Actualizar producto");
            System.out.println("4) Eliminar producto");
            System.out.println("5) Crear un pedido");
            System.out.println("6) Listar pedidos");
            System.out.println("7) Salir");
            System.out.print("Elija una opción: ");

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intente con un número.");
                continue;
            }

            switch (option) {
                case 1:
                    System.out.print("Nombre del producto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Precio: ");
                    double precio = Double.parseDouble(scanner.nextLine());
                    System.out.print("Stock: ");
                    int stock = Integer.parseInt(scanner.nextLine());
                    productoService.agregarProducto(nombre, precio, stock);
                    break;
                case 2:
                    productoService.listarProductos();
                    break;
                case 3:
                    System.out.print("ID del producto a actualizar: ");
                    int idAct = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nuevo precio (o presione Enter para dejar igual): ");
                    String precioStr = scanner.nextLine();
                    Double nuevoPrecio = precioStr.isEmpty() ? null : Double.parseDouble(precioStr);
                    System.out.print("Nuevo stock (o presione Enter para dejar igual): ");
                    String stockStr = scanner.nextLine();
                    Integer nuevoStock = stockStr.isEmpty() ? null : Integer.parseInt(stockStr);
                    productoService.actualizarProducto(idAct, nuevoPrecio, nuevoStock);
                    break;
                case 4:
                    System.out.print("ID del producto a eliminar: ");
                    int idEliminar = Integer.parseInt(scanner.nextLine());
                    productoService.eliminarProducto(idEliminar);
                    break;
                case 5:
                    pedidoService.crearPedido();
                    break;
                case 6:
                    pedidoService.listarPedidos();
                    break;
                case 7:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (option != 7);

        scanner.close();
    }
}
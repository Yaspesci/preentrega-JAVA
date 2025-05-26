package techlab.proyecto.productos;

import java.util.ArrayList;
import java.util.List;

public class ProductoService {
    private List<Producto> productos;

    public ProductoService() {
        productos = new ArrayList<>();
    }

    // Agregar un producto
    public void agregarProducto(String nombre, double precio, int stock) {
        Producto nuevo = new Producto(nombre, precio, stock);
        productos.add(nuevo);
        System.out.println("Producto agregado: " + nuevo);
    }

    // Listar todos los productos
    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            System.out.println("Listado de productos:");
            for (Producto p : productos) {
                System.out.println(p);
            }
        }
    }

    // Buscar por ID
    public Producto buscarPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // Buscar por nombre
    public Producto buscarPorNombre(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    // Actualizar precio y stock
    public boolean actualizarProducto(int id, Double nuevoPrecio, Integer nuevoStock) {
        Producto p = buscarPorId(id);
        if (p != null) {
            if (nuevoPrecio != null && nuevoPrecio >= 0) {
                p.setPrecio(nuevoPrecio);
            }
            if (nuevoStock != null && nuevoStock >= 0) {
                p.setStock(nuevoStock);
            }
            System.out.println("Producto actualizado: " + p);
            return true;
        } else {
            System.out.println("Producto no encontrado.");
            return false;
        }
    }

    // Eliminar producto
    public boolean eliminarProducto(int id) {
        Producto p = buscarPorId(id);
        if (p != null) {
            productos.remove(p);
            System.out.println("Producto eliminado: " + p);
            return true;
        } else {
            System.out.println("Producto no encontrado.");
            return false;
        }
    }

    // Getter de la lista (para usarla en pedidos)
    public List<Producto> getProductos() {
        return productos;
    }
}
package techlab.proyecto.pedidos;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contadorId = 1;

    private int id;
    private List<LineaPedido> lineas;

    public Pedido() {
        this.id = contadorId++;
        this.lineas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void agregarLinea(LineaPedido linea) {
        lineas.add(linea);
    }

    public List<LineaPedido> getLineas() {
        return lineas;
    }

    public double calcularTotal() {
        double total = 0;
        for (LineaPedido linea : lineas) {
            total += linea.calcularSubtotal();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Pedido ID: " + id + "\n");
        for (LineaPedido linea : lineas) {
            sb.append("  ").append(linea).append("\n");
        }
        sb.append("Total: $").append(calcularTotal());
        return sb.toString();
    }
}
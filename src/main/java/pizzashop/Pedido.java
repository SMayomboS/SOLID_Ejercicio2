package pizzashop;

import java.util.List;

public class Pedido {
    private final int id;
    private final List<Pizza> pizzas;
    private double total;
    private String estado;

    public Pedido(int id, List<Pizza> pizzas, double total) {
        this.id = id;
        this.pizzas = pizzas;
        this.total = total;
        this.estado = "En proceso";
    }

    public int getId() { return id; }
    public List<Pizza> getPizzas() { return pizzas; }
    public double getTotal() { return total; }
    public String getEstado() { return estado; }

    public void cancelar() {
        this.estado = "Cancelado";
    }

    public void pagar() {
        this.estado = "Pagado";
    }
}

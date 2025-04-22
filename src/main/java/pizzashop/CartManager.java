package pizzashop;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private final List<Pizza> carrito = new ArrayList<>();

    public void añadirPizza(Pizza pizza) {
        carrito.add(pizza);
    }

    public List<Pizza> getCarrito() {
        return carrito;
    }

    public double calcularTotal() {
        return carrito.stream().mapToDouble(Pizza::getPrecio).sum();
    }

    public void vaciarCarrito() {
        carrito.clear();
    }
}

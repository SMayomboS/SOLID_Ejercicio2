package pizzashop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CartManagerTest {

    @Test
    void testAñadirPizzaYCalcularTotal() {
        CartManager cart = new CartManager();
        Pizza pizza1 = new Pizza("Margarita", "Pequeña", 5.0);
        Pizza pizza2 = new Pizza("Pepperoni", "Grande", 11.0);

        cart.añadirPizza(pizza1);
        cart.añadirPizza(pizza2);

        assertEquals(2, cart.getCarrito().size());
        assertEquals(16.0, cart.calcularTotal());
    }

    @Test
    void testVaciarCarrito() {
        CartManager cart = new CartManager();
        cart.añadirPizza(new Pizza("4 Quesos", "Mediana", 9.0));

        cart.vaciarCarrito();

        assertTrue(cart.getCarrito().isEmpty());
    }
}

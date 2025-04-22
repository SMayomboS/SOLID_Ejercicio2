package pizzashop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class OrderManagerTest {

    @Test
    void testCancelOrderEmptiesCart() {
        DataBaseManager dbManager = new DataBaseManager();
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        CartManager cartManager = new CartManager();
        OrderManager orderManager = new OrderManager(dbManager, paymentProcessor, cartManager);

        // Añadir pizzas al carrito
        cartManager.añadirPizza(new Pizza("Margarita", "Mediana", 10.0));
        cartManager.añadirPizza(new Pizza("Pepperoni", "Grande", 15.0));

        // Asegurarse de que el carrito no está vacío antes de cancelar
        List<Pizza> carritoAntes = cartManager.getCarrito();
        assertFalse(carritoAntes.isEmpty(), "El carrito no debería estar vacío antes de cancelar el pedido.");

        // Cancelar el pedido
        orderManager.cancelOrder(1);

        // Verificar que el carrito está vacío después de cancelar
        List<Pizza> carritoDespues = cartManager.getCarrito();
        assertTrue(carritoDespues.isEmpty(), "El carrito debería estar vacío después de cancelar el pedido.");
    }
}
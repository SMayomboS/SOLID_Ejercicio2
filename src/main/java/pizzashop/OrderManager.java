package pizzashop;

import java.util.List;

public class OrderManager {
    private final DataBaseManager dbManager;
    private final PaymentProcessor paymentProcessor;
    private final CartManager cartManager;

    private int currentPedidoId = -1;

    public OrderManager(DataBaseManager dbManager, PaymentProcessor paymentProcessor, CartManager cartManager) {
        this.dbManager = dbManager;
        this.paymentProcessor = paymentProcessor;
        this.cartManager = cartManager;
    }

    public void createOrder(List<Pizza> pizzas, double total) {
        if (paymentProcessor.processPayment(total)) {
            int pedidoId = dbManager.generatePedidoId();
            Pedido pedido = new Pedido(pedidoId, pizzas, total);
            dbManager.saveOrder(pedido);
            currentPedidoId = pedidoId;
        }
    }

    public void cancelOrder() {
        if (currentPedidoId != -1) {
            dbManager.deleteOrder(currentPedidoId);
            cartManager.vaciarCarrito();
            currentPedidoId = -1;
        }
    }
}

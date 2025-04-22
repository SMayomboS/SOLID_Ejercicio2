package pizzashop;

public class OrderManager {
    private final DataBaseManager dbManager;
    private final PaymentProcessor paymentProcessor;
    private final CartManager cartManager;

    public OrderManager(DataBaseManager dbManager, PaymentProcessor paymentProcessor, CartManager cartManager) {
        this.dbManager = dbManager;
        this.paymentProcessor = paymentProcessor;
        this.cartManager = cartManager;
    }

    public void createOrder(String orderDetails, double amount) {
        if (paymentProcessor.processPayment(amount)) {
            dbManager.saveOrder(orderDetails);
        }
    }

    public void cancelOrder(int orderId) {
        if (dbManager != null) {
            dbManager.deleteOrder(orderId);
            cartManager.vaciarCarrito(); // Vacía el carrito al cancelar el pedido
        } else {
            throw new IllegalStateException("DataBaseManager no está inicializado.");
        }
    }
}
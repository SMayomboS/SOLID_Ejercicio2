package pizzashop;

public class OrderManager {
    private final DataBaseManager dbManager;
    private final PaymentProcessor paymentProcessor;

    public OrderManager(DataBaseManager dbManager, PaymentProcessor paymentProcessor) {
        this.dbManager = dbManager;
        this.paymentProcessor = paymentProcessor;
    }

    public void createOrder(String orderDetails, double amount) {
        if (paymentProcessor.processPayment(amount)) {
            dbManager.saveOrder(orderDetails);
        }
    }

    public void cancelOrder(int orderId) {
        dbManager.deleteOrder(orderId);
    }
}
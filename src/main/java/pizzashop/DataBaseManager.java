package pizzashop;

import java.util.HashMap;
import java.util.Map;

class DataBaseManager {
    private final Map<String, String> users = new HashMap<>();

    public void connect() {
        System.out.println("Conectando a la base de datos...");
    }

    public void saveOrder(String orderDetails) {
        System.out.println("Guardando pedido: " + orderDetails);
    }

    public void deleteOrder(int orderId) {
        System.out.println("Eliminando pedido: " + orderId);
    }

    public void saveUser(String username, String password) {
        users.put(username, password);
        System.out.println("Usuario guardado: " + username);
    }

    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    public String getPassword(String username) {
        return users.get(username);
    }
}
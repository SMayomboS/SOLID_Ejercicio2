package pizzashop;

import java.util.HashMap;
import java.util.Map;

public class DataBaseManager {
    private final Map<String, String> users = new HashMap<>();
    private final Map<Integer, Pedido> pedidos = new HashMap<>();
    private int nextPedidoId = 1;

    public void saveUser(String username, String password) {
        users.put(username, password);
    }

    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    public String getPassword(String username) {
        return users.get(username);
    }

    public int generatePedidoId() {
        return nextPedidoId++;
    }

    public void saveOrder(Pedido pedido) {
        pedidos.put(pedido.getId(), pedido);
        pedido.pagar(); // Se marca como pagado al guardar
    }

    public void deleteOrder(int pedidoId) {
        Pedido pedido = pedidos.get(pedidoId);
        if (pedido != null) {
            pedido.cancelar();
        }
    }

    public Pedido getPedido(int pedidoId) {
        return pedidos.get(pedidoId);
    }
}

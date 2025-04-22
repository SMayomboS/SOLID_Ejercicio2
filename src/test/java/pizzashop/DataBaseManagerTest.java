package pizzashop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DataBaseManagerTest {

    @Test
    void testSaveAndGetUser() {
        DataBaseManager dbManager = new DataBaseManager();
        dbManager.saveUser("testUser", "testPass");

        assertTrue(dbManager.userExists("testUser"));
        assertEquals("testPass", dbManager.getPassword("testUser"));
    }

    @Test
    void testSaveAndDeleteOrder() {
        DataBaseManager dbManager = new DataBaseManager();
        assertDoesNotThrow(() -> {
            dbManager.saveOrder("Pedido de prueba");
            dbManager.deleteOrder(1);
        });
    }
}

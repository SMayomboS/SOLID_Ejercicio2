package pizzashop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthenticatorTest {

    @Test
    void testRegisterAndAuthenticate() {
        DataBaseManager dbManager = new DataBaseManager();
        Authenticator auth = new Authenticator(dbManager);

        assertTrue(auth.register("usuario", "clave123"));
        assertTrue(auth.authenticate("usuario", "clave123"));
    }

    @Test
    void testDuplicateRegistration() {
        DataBaseManager dbManager = new DataBaseManager();
        Authenticator auth = new Authenticator(dbManager);

        auth.register("usuario", "clave123");
        assertFalse(auth.register("usuario", "otraClave"));
    }

    @Test
    void testAuthenticationFails() {
        DataBaseManager dbManager = new DataBaseManager();
        Authenticator auth = new Authenticator(dbManager);

        assertFalse(auth.authenticate("noExiste", "password"));
    }
}

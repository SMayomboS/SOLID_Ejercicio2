package pizzashop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentProcessorTest {

    @Test
    void testProcessPayment() {
        PaymentProcessor processor = new PaymentProcessor();
        assertTrue(processor.processPayment(50.0));
    }
}

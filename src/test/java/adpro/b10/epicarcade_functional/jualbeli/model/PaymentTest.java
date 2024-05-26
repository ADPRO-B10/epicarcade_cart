package adpro.b10.epicarcade_functional.jualbeli.model;

import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private Payment payment;

    @BeforeEach
    public void setUp() {
        payment = new Payment("1", "OVO", "order1");
    }

    @Test
    public void testPaymentCreation() {
        assertEquals("1", payment.getId());
        assertEquals("OVO", payment.getMethod());
        assertEquals("order1", payment.getOrderId());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
    }

    @Test
    public void testSetStatus() {
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    public void testSetInvalidStatus() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("INVALID_STATUS");
        });

        assertTrue(exception instanceof IllegalArgumentException);
    }
}
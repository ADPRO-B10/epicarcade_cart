package adpro.b10.epicarcade_functional.jualbeli.model;

import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentMethod;
import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentStatus;
import adpro.b10.epicarcade_functional.jualbeli.model.Order;
import adpro.b10.epicarcade_functional.jualbeli.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PaymentTest {
    private Payment payment;
    private Order order;

    @BeforeEach
    public void setup() {
        order = new Order();
        payment = new Payment(PaymentMethod.DEFAULT, order);
    }

    @Test
    public void testGettersAndSetters() {
        PaymentMethod method = PaymentMethod.DEFAULT;
        payment.setMethod(method);
        assertEquals(method, payment.getMethod());

        PaymentStatus status = PaymentStatus.SUCCESS;
        payment.setStatus(status);
        assertEquals(status, payment.getStatus());

        BigDecimal amount = new BigDecimal("100.00");
        payment.setAmount(amount);
        assertEquals(amount, payment.getAmount());
    }

    @Test
    public void testGenerateId() {
        payment.generateId();
        assertNotNull(payment.getId());
    }

    @Test
    public void testPaymentConstructor() {
        Payment newPayment = new Payment(PaymentMethod.DEFAULT, order);
        assertEquals(PaymentMethod.DEFAULT, newPayment.getMethod());
        assertEquals(PaymentStatus.PENDING, newPayment.getStatus());
        assertEquals(order.getTotalPrice(), newPayment.getAmount());
    }
}
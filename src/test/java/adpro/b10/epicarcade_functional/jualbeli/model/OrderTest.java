package adpro.b10.epicarcade_functional.jualbeli.model;

import adpro.b10.epicarcade_functional.jualbeli.enums.OrderStatus;
import adpro.b10.epicarcade_functional.jualbeli.model.Order;
import adpro.b10.epicarcade_functional.jualbeli.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderTest {
    private Order order;
    private Payment payment;

    @BeforeEach
    public void setup() {
        order = new Order();
        payment = new Payment();
        order.setPayment(payment);
    }

    @Test
    public void testGettersAndSetters() {
        String id = UUID.randomUUID().toString();
        order.setId(id);
        assertEquals(id, order.getId());

        String buyerId = "buyerId";
        order.setBuyerId(buyerId);
        assertEquals(buyerId, order.getBuyerId());

        OrderStatus status = OrderStatus.WAITING_PAYMENT;
        order.setStatus(status);
        assertEquals(status, order.getStatus());

        LocalDateTime orderDate = LocalDateTime.now();
        order.setOrderDate(orderDate);
        assertEquals(orderDate, order.getOrderDate());

        BigDecimal totalPrice = new BigDecimal("100.00");
        order.setTotalPrice(totalPrice);
        assertEquals(totalPrice, order.getTotalPrice());
    }

    @Test
    public void testUpdatePaymentAmount() {
        BigDecimal totalPrice = new BigDecimal("100.00");
        order.setTotalPrice(totalPrice);

        order.updatePaymentAmount();

        assertEquals(totalPrice, payment.getAmount());
    }

    @Test
    public void testOrderConstructor() {
        Order newOrder = new Order();
        assertNotNull(newOrder.getId());
    }
}
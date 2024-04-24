package adpro.b10.epicarcade_functional.jualbeli.service;

import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentStatus;
import adpro.b10.epicarcade_functional.jualbeli.model.Order;
import adpro.b10.epicarcade_functional.jualbeli.model.Payment;
import adpro.b10.epicarcade_functional.jualbeli.repository.OrderRepository;
import adpro.b10.epicarcade_functional.jualbeli.repository.PaymentRepository;
import adpro.b10.epicarcade_functional.jualbeli.strategy.PaymentStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaymentServiceImplTest {
    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PaymentStrategy paymentStrategy;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPayment() {
        Order order = new Order("1", null, "buyer1");
        when(orderRepository.findById("1")).thenReturn(order);

        Payment payment = paymentService.addPayment("OVO", "1");

        assertEquals("OVO", payment.getMethod());
        assertEquals("1", payment.getOrderId());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    public void testSetStatus() {
        Order order = new Order("1", null, "buyer1");
        Payment payment = new Payment("1", "OVO", "1");
        when(orderRepository.findById("1")).thenReturn(order);
        when(paymentRepository.findById("1")).thenReturn(payment);

        Payment updatedPayment = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());

        assertEquals(PaymentStatus.SUCCESS.getValue(), updatedPayment.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    public void testSetStatusOrderNotFound() {
        Payment payment = new Payment("1", "OVO", "1");
        when(orderRepository.findById("1")).thenReturn(null);

        assertThrows(NoSuchElementException.class, () -> {
            paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        });
    }

    @Test
    public void testGetPayment() {
        Payment payment = new Payment("1", "OVO", "1");
        when(paymentRepository.findById("1")).thenReturn(payment);

        Payment result = paymentService.getPayment("1");

        assertEquals(payment, result);
    }

    @Test
    public void testGetAllPayments() {
        List<Payment> payments = Arrays.asList(new Payment("1", "OVO", "1"));
        when(paymentRepository.findAll()).thenReturn(payments);

        List<Payment> result = paymentService.getAllPayments();

        assertEquals(payments, result);
    }

    @Test
    public void testSetPaymentStrategy() {
        paymentService.setPaymentStrategy(paymentStrategy);

        verifyNoInteractions(paymentRepository);
    }

    @Test
    public void testExecutePayment() {
        paymentService.setPaymentStrategy(paymentStrategy);
        paymentService.executePayment(100.0);

        verify(paymentStrategy, times(1)).pay(100.0);
    }
}
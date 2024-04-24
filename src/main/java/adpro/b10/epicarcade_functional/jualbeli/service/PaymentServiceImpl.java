package adpro.b10.epicarcade_functional.jualbeli.service;

import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentStatus;
import adpro.b10.epicarcade_functional.jualbeli.model.Order;
import adpro.b10.epicarcade_functional.jualbeli.model.Payment;
import adpro.b10.epicarcade_functional.jualbeli.repository.OrderRepository;

import adpro.b10.epicarcade_functional.jualbeli.repository.PaymentRepository;
import adpro.b10.epicarcade_functional.jualbeli.strategy.PaymentStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final OrderRepository orderRepository;

    private PaymentStrategy paymentStrategy;

    public PaymentServiceImpl(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Payment addPayment(String method, String orderId) {
        Order order = orderRepository.findById(orderId);
        Payment payment = new Payment(method, order.getId());
        paymentRepository.save(payment);
        return payment;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        Order order = orderRepository.findById(payment.getOrderId());
        if (order != null) {
            if (PaymentStatus.contains(status)) {
                order.setStatus(status);
            } else {
                throw new IllegalArgumentException();
            }
            orderRepository.save(order);
            payment.setStatus(status);
            paymentRepository.save(payment);
        } else {
            throw new NoSuchElementException();
        }

        return payment;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    @Override
    public void executePayment(double amount) {
        paymentStrategy.pay(amount);
    }
}
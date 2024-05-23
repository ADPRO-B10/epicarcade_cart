package adpro.b10.epicarcade_functional.jualbeli.service;

import adpro.b10.epicarcade_functional.jualbeli.enums.OrderStatus;
import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentStatus;
import adpro.b10.epicarcade_functional.jualbeli.model.Order;
import adpro.b10.epicarcade_functional.jualbeli.model.Payment;
import adpro.b10.epicarcade_functional.jualbeli.repository.OrderRepository;
import adpro.b10.epicarcade_functional.jualbeli.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    @Override
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Optional<Order> updateOrderStatus(String id, OrderStatus status) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setStatus(status);
            orderRepository.save(order);
        }
        return orderOptional;
    }

    @Override
    public Payment createPayment(String orderId, Payment payment) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            payment.setOrder(order);
            return paymentRepository.save(payment);
        }
        return null;
    }

    @Override
    public Optional<Payment> updatePaymentStatus(String orderId, PaymentStatus status) {
        Optional<Payment> paymentOptional = paymentRepository.findByOrderId(orderId);
        if (paymentOptional.isPresent()) {
            Payment payment = paymentOptional.get();
            payment.setStatus(status);
            paymentRepository.save(payment);
        }
        return paymentOptional;
    }
}
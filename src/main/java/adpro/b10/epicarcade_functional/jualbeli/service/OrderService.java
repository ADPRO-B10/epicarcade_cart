package main.java.adpro.b10.epicarcade_functional.jualbeli.service;

import adpro.b10.epicarcade_functional.jualbeli.enums.OrderStatus;
import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentStatus;
import adpro.b10.epicarcade_functional.jualbeli.model.Order;
import adpro.b10.epicarcade_functional.jualbeli.model.Payment;

import java.util.Optional;

public interface OrderService {
    Order saveOrder(Order order);
    Order getOrderByUserId(String userId);
    Optional<Order> getOrderById(String id);
    void deleteOrder(String id);
    Optional<Order> updateOrderStatus(String id, OrderStatus status);
    Payment createPayment(String orderId, Payment payment);
    Optional<Payment> updatePaymentStatus(String orderId, PaymentStatus status);
}
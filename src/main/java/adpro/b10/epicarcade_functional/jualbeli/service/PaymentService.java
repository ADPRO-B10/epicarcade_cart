package adpro.b10.epicarcade_functional.jualbeli.service;

import adpro.b10.epicarcade_functional.jualbeli.model.Order;
import adpro.b10.epicarcade_functional.jualbeli.model.Payment;

import java.util.List;
import java.util.Map;

public interface PaymentService {
    public Payment addPayment(String method, String orderId);

    public Payment setStatus(Payment payment, String status);

    public Payment getPayment(String paymentId);

    public List<Payment> getAllPayments();

    public void setPaymentStrategy(PaymentStrategy paymentStrategy);

    public void executePayment(double amount);
}
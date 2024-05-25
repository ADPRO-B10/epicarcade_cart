package adpro.b10.epicarcade_functional.jualbeli.service;

import adpro.b10.epicarcade_functional.jualbeli.model.Payment;

public interface PaymentService {
    Payment createPayment(Payment payment);
    Payment updatePaymentMethod(String paymentId, String newMethod);
    Payment getPaymentByOrderId(String orderId);
    void applyPaymentStrategy(String paymentId, PaymentStrategy strategy);
}
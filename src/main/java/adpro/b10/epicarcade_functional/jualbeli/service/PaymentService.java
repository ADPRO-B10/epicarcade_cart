package adpro.b10.epicarcade_functional.jualbeli.service;

import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentStatus;
import adpro.b10.epicarcade_functional.jualbeli.model.Payment;

import java.util.Optional;

public interface PaymentService {
    Optional<Payment> savePayment(Payment payment);
    Optional<Payment> getPaymentById(Long id);
    void deletePayment(Long id);
    Optional<Payment> updatePaymentStatus(Long id, PaymentStatus status);
    Optional<Payment> getPaymentByOrderId(Long orderId);
    Optional<Payment> applyPaymentStrategy(Long paymentId, PaymentStrategy strategy);
}
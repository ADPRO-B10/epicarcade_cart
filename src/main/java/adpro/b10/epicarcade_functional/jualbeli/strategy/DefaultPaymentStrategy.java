package adpro.b10.epicarcade_functional.jualbeli.strategy;

import adpro.b10.epicarcade_functional.jualbeli.model.Payment;
import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentStatus;

import java.util.Optional;

public class DefaultPaymentStrategy implements PaymentStrategy {
    @Override
    public Optional<PaymentStatus> processPayment(Payment payment) {
        // Implement default payment processing
        payment.setStatus(PaymentStatus.SUCCESS);
        return Optional.of(PaymentStatus.SUCCESS);
    }
}
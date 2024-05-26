package adpro.b10.epicarcade_functional.jualbeli.strategy;

import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentStatus;
import adpro.b10.epicarcade_functional.jualbeli.model.Payment;

import java.util.Optional;

public interface PaymentStrategy {
    Optional<PaymentStatus> processPayment(Payment payment);
}
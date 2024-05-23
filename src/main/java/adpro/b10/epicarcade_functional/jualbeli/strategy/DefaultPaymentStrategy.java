package main.java.adpro.b10.epicarcade_functional.jualbeli.strategy;

import adpro.b10.epicarcade_functional.jualbeli.model.Payment;
import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentStatus;

public class DefaultPaymentStrategy implements PaymentStrategy {
    @Override
    public void apply(Payment payment) {
        // Implement default payment processing
        payment.setStatus(PaymentStatus.PAID);
    }
}
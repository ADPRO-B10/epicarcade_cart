package adpro.b10.epicarcade_functional.jualbeli.strategy;

import adpro.b10.epicarcade_functional.jualbeli.model.Payment;

public interface PaymentStrategy {
    void apply(Payment payment);
}
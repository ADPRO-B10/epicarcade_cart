package adpro.b10.epicarcade_functional.jualbeli.repository;

import adpro.b10.epicarcade_functional.jualbeli.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}
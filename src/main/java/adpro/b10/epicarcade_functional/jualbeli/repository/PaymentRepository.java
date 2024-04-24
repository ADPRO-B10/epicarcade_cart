package adpro.b10.epicarcade_functional.jualbeli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import adpro.b10.epicarcade_functional.jualbeli.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}
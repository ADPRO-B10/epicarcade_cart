package adpro.b10.epicarcade_functional.jualbeli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import adpro.b10.epicarcade_functional.jualbeli.model.Order;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, String> {
    Optional<Order> findByBuyerId(String buyerId);
}
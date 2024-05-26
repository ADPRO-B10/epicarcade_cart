package adpro.b10.epicarcade_functional.jualbeli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import adpro.b10.epicarcade_functional.jualbeli.model.Order;

public interface OrderRepository extends JpaRepository<Order, String> {
    @Query("SELECT o FROM Order o WHERE o.buyerId = :buyerId")
    List<Order> findAllByBuyer_Id(@Param("buyerId") String buyerId);
}
package adpro.b10.epicarcade_functional.jualbeli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import adpro.b10.epicarcade_functional.jualbeli.model.OrderGame;

import java.util.List;
import java.util.Optional;

public interface OrderGameRepository extends JpaRepository<OrderGame, String> {
    @Query("SELECT og FROM OrderGame og WHERE og.order.id = :orderId")
    Optional<List<OrderGame>> findByOrderId(@Param("orderId") String orderId);
}
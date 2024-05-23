package main.java.adpro.b10.epicarcade_functional.jualbeli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import adpro.b10.epicarcade_functional.jualbeli.model.OrderGame;

public interface OrderGameRepository extends JpaRepository<OrderGame, Long> {
    List<OrderGame> findByOrder_Id(Long orderId);
    List<OrderGame> findByGame_Id(Long gameId);
}
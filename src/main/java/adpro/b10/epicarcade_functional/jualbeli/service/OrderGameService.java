package main.java.adpro.b10.epicarcade_functional.jualbeli.service;

import main.java.adpro.b10.epicarcade_functional.jualbeli.model.OrderGame;

import java.util.List;
import java.util.Optional;

public interface OrderGameService {
    OrderGame saveOrderGame(OrderGame orderGame);
    Optional<OrderGame> getOrderGameById(Long id);
    void deleteOrderGame(Long id);
    List<OrderGame> getOrderGamesByOrderId(Long orderId);
    List<OrderGame> getOrderGamesByGameId(Long gameId);
}
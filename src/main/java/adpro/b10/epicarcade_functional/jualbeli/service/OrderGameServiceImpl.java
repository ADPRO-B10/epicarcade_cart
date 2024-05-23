package main.java.adpro.b10.epicarcade_functional.jualbeli.service;

import main.java.adpro.b10.epicarcade_functional.jualbeli.model.OrderGame;
import main.java.adpro.b10.epicarcade_functional.jualbeli.repository.OrderGameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderGameServiceImpl implements OrderGameService {

    @Autowired
    private OrderGameRepository orderGameRepository;

    @Override
    public OrderGame saveOrderGame(OrderGame orderGame) {
        return orderGameRepository.save(orderGame);
    }

    @Override
    public Optional<OrderGame> getOrderGameById(Long id) {
        return orderGameRepository.findById(id);
    }

    @Override
    public void deleteOrderGame(Long id) {
        orderGameRepository.deleteById(id);
    }

    @Override
    public List<OrderGame> getOrderGamesByOrderId(Long orderId) {
        return orderGameRepository.findByOrderId(orderId);
    }

    @Override
    public List<OrderGame> getOrderGamesByGameId(Long gameId) {
        return orderGameRepository.findByGameId(gameId);
    }
}
package main.java.adpro.b10.epicarcade_functional.jualbeli.controller;

import main.java.adpro.b10.epicarcade_functional.jualbeli.model.OrderGame;
import main.java.adpro.b10.epicarcade_functional.jualbeli.service.OrderGameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orderGames")
public class OrderGameController {

    @Autowired
    private OrderGameService orderGameService;

    @PostMapping
    public ResponseEntity<OrderGame> createOrderGame(@RequestBody OrderGame orderGame) {
        return ResponseEntity.ok(orderGameService.saveOrderGame(orderGame));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderGame> getOrderGameById(@PathVariable Long id) {
        Optional<OrderGame> orderGame = orderGameService.getOrderGameById(id);
        return orderGame.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderGame(@PathVariable Long id) {
        orderGameService.deleteOrderGame(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderGame>> getOrderGamesByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderGameService.getOrderGamesByOrderId(orderId));
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<OrderGame>> getOrderGamesByGameId(@PathVariable Long gameId) {
        return ResponseEntity.ok(orderGameService.getOrderGamesByGameId(gameId));
    }
}
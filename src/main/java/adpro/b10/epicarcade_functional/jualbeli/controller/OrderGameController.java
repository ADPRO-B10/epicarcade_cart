package adpro.b10.epicarcade_functional.jualbeli.controller;

import adpro.b10.epicarcade_functional.jualbeli.dto.OrderGameDto;
import adpro.b10.epicarcade_functional.jualbeli.mapper.OrderGameMapper;
import adpro.b10.epicarcade_functional.jualbeli.model.OrderGame;
import adpro.b10.epicarcade_functional.jualbeli.service.OrderGameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orderGames")
public class OrderGameController {

    @Autowired
    private OrderGameService orderGameService;

    @PostMapping
    public Future<ResponseEntity<OrderGameDto>> createOrderGame(@RequestBody OrderGameDto orderGameDto) throws ExecutionException, InterruptedException {
        return new AsyncResult<>(ResponseEntity.ok(orderGameService.saveOrderGame(orderGameDto).get()));
    }

    @GetMapping("/{id}")
    public Future<ResponseEntity<OrderGameDto>> getOrderGameById(@PathVariable String id) throws ExecutionException, InterruptedException {
        Optional<OrderGameDto> orderGameDto = orderGameService.getOrderGameById(id).get();
        return new AsyncResult<>(orderGameDto.map(ResponseEntity::ok)
                           .orElseGet(() -> ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public Future<ResponseEntity<Void>> deleteOrderGame(@PathVariable String id) throws ExecutionException, InterruptedException {
        orderGameService.deleteOrderGame(id);
        return new AsyncResult<>(ResponseEntity.noContent().build());
    }

    @GetMapping("/order/{orderId}")
    public Future<ResponseEntity<List<OrderGameDto>>> getOrderGamesByOrderId(@PathVariable String orderId) throws ExecutionException, InterruptedException {
        List<OrderGameDto> orderGameDtos = orderGameService.getOrderGamesByOrderId(orderId).get();
        return new AsyncResult<>(ResponseEntity.ok(orderGameDtos));
    }
}
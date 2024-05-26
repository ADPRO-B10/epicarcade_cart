package adpro.b10.epicarcade_functional.jualbeli.service;

import adpro.b10.epicarcade_functional.jualbeli.dto.OrderGameDto;
import adpro.b10.epicarcade_functional.jualbeli.model.OrderGame;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

public interface OrderGameService {
    Future<OrderGameDto> saveOrderGame(OrderGameDto orderGameDto);
    Future<Optional<OrderGameDto>> getOrderGameById(String id);
    void deleteOrderGame(String id);
    Future<List<OrderGameDto>> getOrderGamesByOrderId(String orderId);
}
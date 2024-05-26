package adpro.b10.epicarcade_functional.jualbeli.service;

import adpro.b10.epicarcade_functional.jualbeli.dto.OrderGameDto;
import adpro.b10.epicarcade_functional.jualbeli.mapper.OrderGameMapper;
import adpro.b10.epicarcade_functional.jualbeli.mapper.OrderMapper;
import adpro.b10.epicarcade_functional.jualbeli.model.OrderGame;
import adpro.b10.epicarcade_functional.jualbeli.repository.OrderGameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public class OrderGameServiceImpl implements OrderGameService {

    @Autowired
    private OrderGameRepository orderGameRepository;

    @Autowired
    private OrderGameMapper orderGameMapper;

    @Async
    @Override
    public Future<OrderGameDto> saveOrderGame(OrderGameDto orderGameDto) {
        OrderGame orderGame = orderGameMapper.orderGameDTOToOrderGame(orderGameDto);
        OrderGame savedOrderGame = orderGameRepository.save(orderGame);
        return new AsyncResult<>(orderGameMapper.orderGameToOrderGameDTO(savedOrderGame));
    }

    @Async
    @Override
    public Future<Optional<OrderGameDto>> getOrderGameById(String id) {
        return new AsyncResult<>(Optional.of(orderGameRepository.findById(id)
                .map(orderGameMapper::orderGameToOrderGameDTO).orElse(null)));
    }

    @Async
    @Override
    public void deleteOrderGame(String id) {
        orderGameRepository.deleteById(id);
    }

    @Async
    @Override
    public Future<List<OrderGameDto>> getOrderGamesByOrderId(String orderId) {
        List<OrderGame> orderGames = orderGameRepository.findByOrderId(orderId).get();
        List<OrderGameDto> orderGameDtos = orderGames.stream()
                .map(orderGameMapper::orderGameToOrderGameDTO)
                .collect(Collectors.toList());
        return new AsyncResult<>(orderGameDtos);
    }
}